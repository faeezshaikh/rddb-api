package com.bmx.verde.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bmx.verde.model.InstrumentOpticConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RddbDaoImpl implements RddbDao {
    private static final Logger log = LoggerFactory.getLogger(RddbDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<InstrumentOpticConfig> loadOpticConfigs() {
        log.info("Loading Optic configs");

        List<InstrumentOpticConfig> list = jdbcTemplate.query("SELECT * FROM INSTR_OPTIC_CONFIG",
                (rs, rowNum) -> new InstrumentOpticConfig(rs.getString("instr_optic_config"),
                        rs.getString("description"), rs.getString("flag_default")));

        return list;
    }

    @Transactional
    public int addTypDisp(String cardName, int numWells, String userField, String instrumentType, String activeFlag,
            String productionFlag, String barcode) {

        log.info("Inserting records in TYP_DISPO");
        return jdbcTemplate.update("INSERT INTO typ_dispo"
                + "(name_typ_dispo, nbr_well, user_field,name_typ_instr, card_code, flag_active,flag_production)"
                + "VALUES(?, ?, ?, ?, decode_bar_code.card_type(?), ?, ?)", cardName, numWells, userField,
                instrumentType, barcode, activeFlag, productionFlag);
    }

    @Transactional
    public int addDispoTest(int carouselSlot, String cardName, int defaultExper, int defaultEssay, String barcode,
            int defaultStrainId, String defaultStorageConditions, String defaultBadFlag, String defaultFlagActive,
            String runDate, String serialNumber, String instrumentType, String itgStatusCode, String dilutionType,
            String barcodDispoTest) {

        String formattedDate = formatRunDate(runDate, true);
        // "21-Aug-19 10:16:28"

        int dilution = lookupDilution(dilutionType);
        log.info("Inserting record into DISPO_TEST");
        return jdbcTemplate.update("INSERT INTO DISPO_TEST" + "(NO_DISPO_TEST, " + // nexval - provided
                "LOCA_CONSO_INSTR," + "NAME_TYP_DISPO," + "NO_EXPER_LEVEL," + "NO_ESSAY," + "CTRL_BATCH_NUMB_DISPO,"
                + "BAR_COD_DISPO_TEST," + "NO_NSB_STRAI," + "COD_DISPO_STOR_COND," + "FLAG_BAD_RESUL," + "FLAG_ACTIV,"
                + "DAT_RUN," + "NO_INSTR," + "NAME_TYP_INSTR," + "ITG_STATUS_CODE," + "DILUTION)" +

                // " VALUES
                // (NO_DISPO_TEST.nextval,?,?,?,?,decode_bar_code.lot_number(?),?,?,?,?,?,TO_DATE(
                // '13-JUN-87', 'DD-MON-YY', 'NLS_DATE_LANGUAGE = American' ),?,?,?,?)",
                " VALUES (NO_DISPO_TEST.nextval,?,?,?,?,decode_bar_code.lot_number(?),?,?,?,?,?,TO_DATE( ?, 'DD-MON-YY HH24:MI:SS' ),?,?,?,?)",
                carouselSlot, // LOCA_CONSO_INSTR
                cardName, // NAME_TYP_DISPO
                defaultExper, // NO_EXPER_LEVEL
                defaultEssay, // NO_ESSAY
                barcode, // CTRL_BATCH_NUMB_DISPO
                barcodDispoTest, // BAR_COD_DISPO_TEST
                defaultStrainId, // NO_NSB_STRAI
                defaultStorageConditions, // COD_DISPO_STOR_COND
                defaultBadFlag, // FLAG_BAD_RESUL
                defaultFlagActive, // FLAG_ACTIV
                formattedDate, // DAT_RUN - provided
                serialNumber, // NO_INSTR
                instrumentType, // NAME_TYP_INSTR
                itgStatusCode, // ITG_STATUS_CODE
                dilution // DILUTION
        );
    }

    private String formatRunDate(String runDate, boolean withTS) {
        log.debug("Formatting run date:" + runDate);
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat targetFormat = withTS ? new SimpleDateFormat("dd-MMM-yy hh:mm:ss")
                : new SimpleDateFormat("dd-MMM-yy");

        Date transformedDate = null;
        try {
            transformedDate = originalFormat.parse(runDate);
        } catch (ParseException e) {
            log.error("Exception occured while parsing rundate." + runDate);
        }
        String formattedDate = targetFormat.format(transformedDate); // 20120821
        log.debug("Transformed date:" + formattedDate);
        return formattedDate;
    }

    @Transactional
    public int updateDispoTest(long noDispoTest, String dilutionType, Integer carousel, String instrSerialNumber,
            String instrType, String runDate, String itgStatusCode) {
        String formattedDate = formatRunDate(runDate, true);
        int dilutionId = lookupDilution(dilutionType);
        return jdbcTemplate.update(
                "UPDATE DISPO_TEST set DILUTION = ?, LOCA_CONSO_INSTR = ? , NO_INSTR = ? , NAME_TYP_INSTR = ? , DAT_RUN = TO_DATE( ?, 'DD-MON-YY HH24:MI:SS' ), ITG_STATUS_CODE = ? where NO_DISPO_TEST = ?",
                dilutionId, carousel, instrSerialNumber, instrType, formattedDate, itgStatusCode, noDispoTest);
    }

    private int lookupDilution(String dilutionType) {
        log.info("Looking up Dilution Id from DILUTION table for type: " + dilutionType);
        int dilution = -1;
        List<Object> foo = jdbcTemplate.query(
                "SELECT DILUTION_ID, DESCRIPTION FROM DILUTION WHERE VITEK2S_DILUTION_TYPE = ?",
                new Object[] { dilutionType }, (rs, rowNum) -> rs.getInt("DILUTION_ID"));
        if (foo != null && foo.size() > 0) {
            dilution = (int) foo.get(0);
        }
        log.debug("Dilution id fetched. It is : " + dilution);
        return dilution;
    }

}
