package com.bmx.verde.service;

import com.bmx.verde.dao.RddbDao;
import com.bmx.verde.dto.Card;
import com.bmx.verde.dto.CardReading;
import com.bmx.verde.dto.UpdateInstrumentEventDto;
import com.bmx.verde.dto.UpdateLocationDto;
import com.bmx.verde.dto.UpdateLocationSiteDto;
import com.bmx.verde.model.DispoTest;
import com.bmx.verde.model.Instrument;
import com.bmx.verde.model.InstrumentId;
import com.bmx.verde.model.InstrumentOpticConfig;
import com.bmx.verde.model.LocateInstrument;
import com.bmx.verde.model.LocateInstrumentId;
import com.bmx.verde.model.Reading;
import com.bmx.verde.model.ReadingPk;
import com.bmx.verde.model.TypDispo;
import com.bmx.verde.repository.DispoTestRepository;
import com.bmx.verde.repository.InstrumentRepository;
import com.bmx.verde.repository.LocateInstrumentRepository;
import com.bmx.verde.repository.ReadingRepository;
import com.bmx.verde.repository.TypDispoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the RddbWritebackService
 */
@Service
@Transactional
public class RddbWritebackServiceImpl implements RddbWritebackService {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(RddbWritebackServiceImpl.class);

    @Autowired
    private InstrumentRepository repo;

    @Autowired
    private ReadingRepository readingRepo;

    @Autowired
    private RddbDao dao;

    @Autowired
    private TypDispoRepository typDispoRepo;

    @Autowired
    private DispoTestRepository dispoTestRepo;

    @Autowired
    private LocateInstrumentRepository locateInstrumentRepository;

    @Value("${app.defaultExper}")
    private int defaultExper;

    @Value("${app.defaultEssay}")
    private int defaultEssay;

    @Value("${app.defaultStrainId}")
    private int defaultStrainId;

    @Value("${app.defaultStorageConditions}")
    private String defaultStorageConditions;

    @Value("${app.defaultBadFlag}")
    private String defaultBadFlag;

    @Value("${app.defaultFlagActive}")
    private String defaultFlagActive;

    @Value("${app.itgStatusCode}")
    private String itgStatusCode;

    @Value("${app.flagActive}")
    private String flagActive;

    @Value("${app.flagProduction}")
    private String flagProduction;

    @Value("${app.defaultUserField}")
    private String defaultUserField;

    private long noDispoTest = -1L;

    @Value("${sqs.url}")
    private String sqsURL;

    public void processInstrumentMessage(com.bmx.verde.dto.CreateInstrumentDto instrument) {
        log.info("Creating new instrument in RDDB Oracle database.");
        createInstrumentIfNotPresent(instrument.getSerialNumber(), instrument.getInstrumentType(), instrument.getComment(),
                instrument.getOpticConfig());
    }

    public void processUpdateInstrumentMessage(UpdateInstrumentEventDto updateEvent) {
        String serialNumber = updateEvent.getSerialNumber();
        String instrumentType = updateEvent.getInstrumentType();
        log.info("Updating instrument in RDDB Oracle database. Serial: {} Type: {}", serialNumber, instrumentType);

        Optional<Instrument> instrumentOptional = repo.findById(new InstrumentId(instrumentType, serialNumber));

        if (instrumentOptional.isPresent()) {
            Instrument instrumentModel = instrumentOptional.get();
            instrumentModel.setComInstr(updateEvent.getComment());
            instrumentModel.setInstrOpticConfig(updateEvent.getOpticConfig());
            repo.save(instrumentModel);
            log.info("Instrument {} , Type {} successfully updated in  RDDB.", serialNumber, instrumentType);
        } else {
            log.info("Instrument {} , Type {} does not exist. Not updating. Exiting.", serialNumber, instrumentType);
        }

    }

    public List<InstrumentOpticConfig> loadOpticConfigs() {
        return dao.loadOpticConfigs();
    }

    public String getValidOpticConfig(String instrumentConfig) {
        List<InstrumentOpticConfig> configs = loadOpticConfigs();
        if (configs == null || configs.size() == 0) {
            // If there are no optic configs in the db to reference, return invalid.
            return null;
        }
        InstrumentOpticConfig defaultConfig = configs.stream().filter(c -> c.getFlagDefault().equalsIgnoreCase("Y"))
                .findFirst().get();
        if (StringUtils.isBlank(instrumentConfig)) {
            return defaultConfig.getInstrOpticConfig();
        } else {
            InstrumentOpticConfig existingConfig = configs.stream()
                    .filter(c -> instrumentConfig.equals(c.getInstrOpticConfig())).findAny().orElse(null);
            if (existingConfig == null) {
                return null;
            } // not a valid config
            else
                return instrumentConfig;
        }
    }

    private void createInstrumentIfNotPresent(String serialNumber, String type, String comments, String opticConfig) {
        Optional<Instrument> instrumentOptional = repo.findById(new InstrumentId(type, serialNumber));

        if (!instrumentOptional.isPresent()) {
            InstrumentId iid = new InstrumentId();

            String validOpticConfig = getValidOpticConfig(opticConfig);
            iid.setNameTypeInstr(type);
            iid.setNoInstr(serialNumber);
            Instrument newInstrument = new Instrument();
            newInstrument.setComInstr(comments);
            newInstrument.setInstrOpticConfig(validOpticConfig);
            newInstrument.setWorkstationId(0); // This needs to go. The RDDB schema first needs to be updated.
            newInstrument.setId(iid);
            repo.save(newInstrument);

            log.info("Instrument {} , Type {} successfully created in  RDDB.", serialNumber, type);
        } else {
            log.info("Instrument {} , Type {} already exists. Not creating new instrument", serialNumber, type);
        }

    }

    public void processCardReadingMessage(Card c) {
        if (c == null) {
            log.info("Card is null. Exiting.");
            return;
        }
        this.noDispoTest = -1L;
        String barcodeNumber = "", serialNumber = "";

        int numberOfWells = c.getNumberOfWells();
        log.info("Number of wells on the card :" + numberOfWells);
        barcodeNumber = c.getBarcode();
        serialNumber = c.getInstrumentSerialNumber();

        if (c.getInstrument() != null) {
            log.info("If instrument doesn't exist, it will be created for barcode {}", barcodeNumber);
            createInstrumentIfNotPresent(c.getInstrument().getNoInstr(), c.getInstrument().getNameTypInstr(),
                    c.getInstrument().getComInstr(), c.getInstrument().getInstrOpticConfig());
        }
        log.debug("Looking up Barcode in Dispo_TEST. Barcode Number {} " + barcodeNumber);

        // Step 1. Get DIPO_TEST by barcode number.
        Optional<DispoTest> d = dispoTestRepo.findByBarcodeNumber(barcodeNumber);
        String existingItgStatusCode = null;
        // Step 1A: Is DISPO_TEST present, get 'noDispoTest'
        if (d.isPresent()) {
            noDispoTest = d.get().getNoDispoTest();
            log.info("Dispo Test Exists. DispoTest # : " + noDispoTest);
            // Step 1A-A: Jira BMX-776: If ITG_STATUS_CODE = "E", dont add readings, simply
            // return;
            existingItgStatusCode = d.get().getItgStatusCode();
            log.info("The ITG_STATUS_CODE for the DISPO_TEST is {}", existingItgStatusCode);
            if (existingItgStatusCode != null && (existingItgStatusCode.equalsIgnoreCase("E") || existingItgStatusCode.equalsIgnoreCase("I"))) {
                log.info(
                        "The Integration status code is {}. Hence skipping further processing. Barcode {},  Serial Number {}",
                        existingItgStatusCode, barcodeNumber, serialNumber);
                return;
            }
        } else {
            // Step 1B: If DISPO_TEST not found, it needs to be created.
            log.info("Dispo Test does not exist for this barcode: " + barcodeNumber
                    + ". A new dispo_test record needs to be created.");

            // Step 1C: Before creating dispo_test check if name_typ_dispo exists in
            // typ_dispo
            // test.
            // If it doesnt exist, create it and then create record in dispo_test

            log.info("Checking if Name_Typ_Dispo of : " + c.getCardName() + " exists in TYP_DISPO table");
            // (From Don: 'name_typ_dispo' is card name)
            Optional<TypDispo> typDispo = typDispoRepo.findByNameTypDispo(c.getCardName());
            // Step 1D: Do Lookup. Check if Typ_Dispo exists
            if (!typDispo.isPresent()) {
                log.info("Name_Typ_Dispo of : " + c.getCardName()
                        + " does not exist in TYP_DISPO table. Inserting now..");

                String userField = defaultUserField;
                // dao.addTypDisp(c.getCardName(), numberOfWells, userField, "VITEK2XL", "Y",
                // "Y",barcodeNumber);

                dao.addTypDisp(c.getCardName(), numberOfWells, userField, c.getInstrumentType(), flagActive,
                        flagProduction, barcodeNumber);

                log.info("Successfully created name_typ_dispo of : " + c.getCardType() + " in TYP_DISPO table.");
                log.info("Creating DISPO_TEST now..");
            } else {
                log.info(c.getCardName() + " exists in TYP_DISPO table. Inserting in DIPO_TEST");
            }
            // Since no dispo_test exists, set status code to 'N'. Jira BMX-776
            itgStatusCode = "N";
            dao.addDispoTest(c.getCarouselSlot().intValue(), c.getCardName(), defaultExper, defaultEssay,
                    c.getBarcode(), defaultStrainId, defaultStorageConditions, defaultBadFlag, defaultFlagActive,
                    c.getFirstReadTimeStamp(), c.getInstrumentSerialNumber(), c.getInstrumentType(), itgStatusCode,
                    c.getDilutionType(), c.getBarcode());

            log.info("New Dispo Test record successfully created..");
            Optional<DispoTest> dispoTest = dispoTestRepo.findByBarcodeNumber(barcodeNumber);
            if (dispoTest.isPresent()) {
                noDispoTest = dispoTest.get().getNoDispoTest();
                log.info("The new dispo test that was created with a Dispo Test Number of : " + noDispoTest);
            }
        }
        List<Reading> readings = null;
        readings = insertReadings(c, numberOfWells, noDispoTest);

        log.info(
                readings.size()
                        + " readings successfully sent for saving in RDDB. Barcode Number: {}. Updating DISPO_TEST.",
                barcodeNumber);

        String itgStatusCode = determineItgStatusCode(c.getCardStatus());
        if (itgStatusCode != null && itgStatusCode.equalsIgnoreCase("P")) {
            if (existingItgStatusCode != null && existingItgStatusCode.equalsIgnoreCase("P")) {
                log.info("ITG_STATUS_CODE column in DISPO_TEST is already {}. No need to update.", existingItgStatusCode);
                return;
            }
        }

        if (itgStatusCode != null) {
            log.info("Updating ITG_STATUS_CODE column in DISPO_TEST. Setting it to {}", itgStatusCode);
            int rows = dao.updateDispoTest(noDispoTest, c.getDilutionType(), c.getCarouselSlot(),
                    c.getInstrumentSerialNumber(), c.getInstrumentType(), c.getFirstReadTimeStamp(), itgStatusCode);
            log.info(rows + " row(s) in DISPO_TEST successfully updated. ITG_STATUS_CODE successfully set to {}",
                    itgStatusCode);
        }
    }

    private List<Reading> insertReadings(Card c, int numberOfWells, long noDispoTest) {
        List<CardReading> readingList = c.getCardReadings();
        Integer innoculationEpoch = Integer.parseInt(c.getInoculationEpoch());
        // CardReading firstReading = readingList.get(0);

        List<Reading> readings = readingList.stream()
                .map(r -> transformReadingObject(r, noDispoTest, numberOfWells, innoculationEpoch))
                .collect(Collectors.toList());
        log.info("Writing " + readings.size() + " card READING records to Oracle..Please wait..");
        readings.forEach(reading -> {
            readingRepo.save(reading);
        });
        return readings;
    }

    public void updateItgStatusCodeAndLog(String barcodeNumber, String serialNumber, Card c, String message) {
        if (noDispoTest == -1) {
            log.info("Since DISPO_TEST does not exist, ITG_STATUS_CODE cannot be set to 'E'. Exiting.");
            return;
        }
        log.error(
                "VITEK_INST_SVC_READING_INSERT_ERROR :: Setting ITG_STATUS_CODE to 'E' for Dipo Test {} Barcode number {}. Instrument serial number {}",
                noDispoTest, barcodeNumber, serialNumber);
        itgStatusCode = "E";

        dao.updateDispoTest(noDispoTest, c.getDilutionType(), c.getCarouselSlot(), c.getInstrumentSerialNumber(),
                c.getInstrumentType(), c.getFirstReadTimeStamp(), itgStatusCode);
        log.info("ITG_STATUS_CODE has successfully been set to E in DISPO_TEST for DispoTest # {}", noDispoTest);
    }

    private String determineItgStatusCode(String cardStatus) {
        // Jira BMX-776
        if (cardStatus != null && cardStatus.equalsIgnoreCase("Ejected")) {
            return "I";
        }
        if (cardStatus != null && !cardStatus.equalsIgnoreCase("Ejected")) {
            return "P";
        }
        return null;
    }

    Reading transformReadingObject(CardReading c, long noDispoTest, int numberOfWells, int innoculationEpoch) {
        Reading reading = new Reading();
        ReadingPk pk = new ReadingPk();

        // 13813311
        pk.setNoDispoTest(noDispoTest);
        pk.setWavelength(c.getWavelength());
        Integer tpsResultNet = (c.getReadingMinute() - innoculationEpoch) / 60;
        pk.setTpsResultNet(tpsResultNet);
        reading.setId(pk);
        int values[] = c.getValues();

        if (Long.valueOf(values[0]) != -1L)
            reading.setWell1(Long.valueOf(values[0]));
        if (Long.valueOf(values[1]) != -1L)
            reading.setWell2(Long.valueOf(values[1]));
        if (Long.valueOf(values[2]) != -1L)
            reading.setWell3(Long.valueOf(values[2]));
        if (Long.valueOf(values[3]) != -1L)
            reading.setWell4(Long.valueOf(values[3]));
        if (Long.valueOf(values[4]) != -1L)
            reading.setWell5(Long.valueOf(values[4]));
        if (Long.valueOf(values[5]) != -1L)
            reading.setWell6(Long.valueOf(values[5]));
        if (Long.valueOf(values[6]) != -1L)
            reading.setWell7(Long.valueOf(values[6]));
        if (Long.valueOf(values[7]) != -1L)
            reading.setWell8(Long.valueOf(values[7]));
        if (Long.valueOf(values[8]) != -1L)
            reading.setWell9(Long.valueOf(values[8]));
        if (Long.valueOf(values[9]) != -1L)
            reading.setWell10(Long.valueOf(values[9]));
        if (Long.valueOf(values[10]) != -1L)
            reading.setWell11(Long.valueOf(values[10]));
        if (Long.valueOf(values[11]) != -1L)
            reading.setWell12(Long.valueOf(values[11]));
        if (Long.valueOf(values[12]) != -1L)
            reading.setWell13(Long.valueOf(values[12]));
        if (Long.valueOf(values[13]) != -1L)
            reading.setWell14(Long.valueOf(values[13]));
        if (Long.valueOf(values[14]) != -1L)
            reading.setWell15(Long.valueOf(values[14]));
        if (Long.valueOf(values[15]) != -1L)
            reading.setWell16(Long.valueOf(values[15]));
        if (Long.valueOf(values[16]) != -1L)
            reading.setWell17(Long.valueOf(values[16]));
        if (Long.valueOf(values[17]) != -1L)
            reading.setWell18(Long.valueOf(values[17]));
        if (Long.valueOf(values[18]) != -1L)
            reading.setWell19(Long.valueOf(values[18]));
        if (Long.valueOf(values[19]) != -1L)
            reading.setWell20(Long.valueOf(values[19]));
        if (Long.valueOf(values[20]) != -1L)
            reading.setWell21(Long.valueOf(values[20]));
        if (Long.valueOf(values[21]) != -1L)
            reading.setWell22(Long.valueOf(values[21]));
        if (Long.valueOf(values[22]) != -1L)
            reading.setWell23(Long.valueOf(values[22]));
        if (Long.valueOf(values[23]) != -1L)
            reading.setWell24(Long.valueOf(values[23]));
        if (Long.valueOf(values[24]) != -1L)
            reading.setWell25(Long.valueOf(values[24]));
        if (Long.valueOf(values[25]) != -1L)
            reading.setWell26(Long.valueOf(values[25]));
        if (Long.valueOf(values[26]) != -1L)
            reading.setWell27(Long.valueOf(values[26]));
        if (Long.valueOf(values[27]) != -1L)
            reading.setWell28(Long.valueOf(values[27]));
        if (Long.valueOf(values[28]) != -1L)
            reading.setWell29(Long.valueOf(values[28]));
        if (Long.valueOf(values[29]) != -1L)
            reading.setWell30(Long.valueOf(values[29]));
        if (Long.valueOf(values[30]) != -1L)
            reading.setWell31(Long.valueOf(values[30]));
        if (Long.valueOf(values[31]) != -1L)
            reading.setWell32(Long.valueOf(values[31]));
        if (Long.valueOf(values[32]) != -1L)
            reading.setWell33(Long.valueOf(values[32]));
        if (Long.valueOf(values[33]) != -1L)
            reading.setWell34(Long.valueOf(values[33]));
        if (Long.valueOf(values[34]) != -1L)
            reading.setWell35(Long.valueOf(values[34]));
        if (Long.valueOf(values[35]) != -1L)
            reading.setWell36(Long.valueOf(values[35]));
        if (Long.valueOf(values[36]) != -1L)
            reading.setWell37(Long.valueOf(values[36]));
        if (Long.valueOf(values[37]) != -1L)
            reading.setWell38(Long.valueOf(values[37]));
        if (Long.valueOf(values[38]) != -1L)
            reading.setWell39(Long.valueOf(values[38]));
        if (Long.valueOf(values[39]) != -1L)
            reading.setWell40(Long.valueOf(values[39]));
        if (Long.valueOf(values[40]) != -1L)
            reading.setWell41(Long.valueOf(values[40]));
        if (Long.valueOf(values[41]) != -1L)
            reading.setWell42(Long.valueOf(values[41]));
        if (Long.valueOf(values[42]) != -1L)
            reading.setWell43(Long.valueOf(values[42]));
        if (Long.valueOf(values[43]) != -1L)
            reading.setWell44(Long.valueOf(values[43]));
        if (Long.valueOf(values[44]) != -1L)
            reading.setWell45(Long.valueOf(values[44]));
        if (Long.valueOf(values[45]) != -1L)
            reading.setWell46(Long.valueOf(values[45]));
        if (Long.valueOf(values[46]) != -1L)
            reading.setWell47(Long.valueOf(values[46]));
        if (Long.valueOf(values[47]) != -1L)
            reading.setWell48(Long.valueOf(values[47]));
        if (Long.valueOf(values[48]) != -1L)
            reading.setWell49(Long.valueOf(values[48]));
        if (Long.valueOf(values[49]) != -1L)
            reading.setWell50(Long.valueOf(values[49]));
        if (Long.valueOf(values[50]) != -1L)
            reading.setWell51(Long.valueOf(values[50]));
        if (Long.valueOf(values[51]) != -1L)
            reading.setWell52(Long.valueOf(values[51]));
        if (Long.valueOf(values[52]) != -1L)
            reading.setWell53(Long.valueOf(values[52]));
        if (Long.valueOf(values[53]) != -1L)
            reading.setWell54(Long.valueOf(values[53]));
        if (Long.valueOf(values[54]) != -1L)
            reading.setWell55(Long.valueOf(values[54]));
        if (Long.valueOf(values[55]) != -1L)
            reading.setWell56(Long.valueOf(values[55]));
        if (Long.valueOf(values[56]) != -1L)
            reading.setWell57(Long.valueOf(values[56]));
        if (Long.valueOf(values[57]) != -1L)
            reading.setWell58(Long.valueOf(values[57]));
        if (Long.valueOf(values[58]) != -1L)
            reading.setWell59(Long.valueOf(values[58]));
        if (Long.valueOf(values[59]) != -1L)
            reading.setWell60(Long.valueOf(values[59]));
        if (Long.valueOf(values[60]) != -1L)
            reading.setWell61(Long.valueOf(values[60]));
        if (Long.valueOf(values[61]) != -1L)
            reading.setWell62(Long.valueOf(values[61]));
        if (Long.valueOf(values[62]) != -1L)
            reading.setWell63(Long.valueOf(values[62]));
        if (Long.valueOf(values[63]) != -1L)
            reading.setWell64(Long.valueOf(values[63]));

        if (numberOfWells == 104) {
            if (Long.valueOf(values[64]) != -1L)
                reading.setWell65(Long.valueOf(values[64]));
            if (Long.valueOf(values[65]) != -1L)
                reading.setWell66(Long.valueOf(values[65]));
            if (Long.valueOf(values[66]) != -1L)
                reading.setWell67(Long.valueOf(values[66]));
            if (Long.valueOf(values[67]) != -1L)
                reading.setWell68(Long.valueOf(values[67]));
            if (Long.valueOf(values[68]) != -1L)
                reading.setWell69(Long.valueOf(values[68]));
            if (Long.valueOf(values[69]) != -1L)
                reading.setWell70(Long.valueOf(values[69]));
            if (Long.valueOf(values[70]) != -1L)
                reading.setWell71(Long.valueOf(values[70]));
            if (Long.valueOf(values[71]) != -1L)
                reading.setWell72(Long.valueOf(values[71]));
            if (Long.valueOf(values[72]) != -1L)
                reading.setWell73(Long.valueOf(values[72]));
            if (Long.valueOf(values[73]) != -1L)
                reading.setWell74(Long.valueOf(values[73]));
            if (Long.valueOf(values[74]) != -1L)
                reading.setWell75(Long.valueOf(values[74]));
            if (Long.valueOf(values[75]) != -1L)
                reading.setWell76(Long.valueOf(values[75]));
            if (Long.valueOf(values[76]) != -1L)
                reading.setWell77(Long.valueOf(values[76]));
            if (Long.valueOf(values[77]) != -1L)
                reading.setWell78(Long.valueOf(values[77]));
            if (Long.valueOf(values[78]) != -1L)
                reading.setWell79(Long.valueOf(values[78]));
            if (Long.valueOf(values[79]) != -1L)
                reading.setWell80(Long.valueOf(values[79]));
            if (Long.valueOf(values[80]) != -1L)
                reading.setWell81(Long.valueOf(values[80]));
            if (Long.valueOf(values[81]) != -1L)
                reading.setWell82(Long.valueOf(values[81]));
            if (Long.valueOf(values[82]) != -1L)
                reading.setWell83(Long.valueOf(values[82]));
            if (Long.valueOf(values[83]) != -1L)
                reading.setWell84(Long.valueOf(values[83]));
            if (Long.valueOf(values[84]) != -1L)
                reading.setWell85(Long.valueOf(values[84]));
            if (Long.valueOf(values[85]) != -1L)
                reading.setWell86(Long.valueOf(values[85]));
            if (Long.valueOf(values[86]) != -1L)
                reading.setWell87(Long.valueOf(values[86]));
            if (Long.valueOf(values[87]) != -1L)
                reading.setWell88(Long.valueOf(values[87]));
            if (Long.valueOf(values[88]) != -1L)
                reading.setWell89(Long.valueOf(values[88]));
            if (Long.valueOf(values[89]) != -1L)
                reading.setWell90(Long.valueOf(values[89]));
            if (Long.valueOf(values[90]) != -1L)
                reading.setWell91(Long.valueOf(values[90]));
            if (Long.valueOf(values[91]) != -1L)
                reading.setWell92(Long.valueOf(values[91]));
            if (Long.valueOf(values[92]) != -1L)
                reading.setWell93(Long.valueOf(values[92]));
            if (Long.valueOf(values[93]) != -1L)
                reading.setWell94(Long.valueOf(values[93]));
            if (Long.valueOf(values[94]) != -1L)
                reading.setWell95(Long.valueOf(values[94]));
            if (Long.valueOf(values[95]) != -1L)
                reading.setWell96(Long.valueOf(values[95]));
            if (Long.valueOf(values[96]) != -1L)
                reading.setWell97(Long.valueOf(values[96]));
            if (Long.valueOf(values[97]) != -1L)
                reading.setWell98(Long.valueOf(values[97]));
            if (Long.valueOf(values[98]) != -1L)
                reading.setWell99(Long.valueOf(values[98]));
            if (Long.valueOf(values[99]) != -1L)
                reading.setWell100(Long.valueOf(values[99]));
            if (Long.valueOf(values[100]) != -1L)
                reading.setWell101(Long.valueOf(values[100]));
            if (Long.valueOf(values[101]) != -1L)
                reading.setWell102(Long.valueOf(values[101]));
            if (Long.valueOf(values[102]) != -1L)
                reading.setWell103(Long.valueOf(values[102]));
            if (Long.valueOf(values[103]) != -1L)
                reading.setWell104(Long.valueOf(values[103]));
        }
        return reading;
    }

    @Override
    public void processUpdateLocation(UpdateLocationDto updateLocationDto) {
        String serialNumber = updateLocationDto.getSerialNumber();
        String instType = updateLocationDto.getInstrumentType();
        UpdateLocationSiteDto currSite = updateLocationDto.getCurrentLocation();
        UpdateLocationSiteDto prevSite = updateLocationDto.getPreviousLocation();
        if (currSite != null) {
            LocateInstrument currentInst = new LocateInstrument(serialNumber, instType, currSite.getBeginDate());
            currentInst.setEndDate(currSite.getEndDate());
            currentInst.setSiteCode(currSite.getSiteCode());
            if (prevSite != null
                    && locateInstrumentRepository.existsBySerialNumberAndInstrumentType(serialNumber, instType)) {
                LocateInstrumentId previousLocId = new LocateInstrumentId(serialNumber, instType,
                        prevSite.getBeginDate());
                Optional<LocateInstrument> prevLoc = locateInstrumentRepository.findById(previousLocId);
                if (prevLoc.isPresent()) {
                    LocateInstrument prevInst = prevLoc.get();
                    prevInst.setEndDate(prevSite.getEndDate());
                    /*
                     * Use existing obsolete software version number value for location record set -
                     * RDDB field is obsolete, but required.
                     */
                    currentInst.setSoftwareVersion(prevInst.getSoftwareVersion());
                    locateInstrumentRepository.saveAndFlush(prevInst);
                }
            }
            locateInstrumentRepository.saveAndFlush(currentInst);
        }
    }

}
