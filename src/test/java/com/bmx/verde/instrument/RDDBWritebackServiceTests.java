package com.bmx.verde.instrument;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.amazonaws.services.sqs.AmazonSQS;
import com.bmx.verde.dao.RddbDao;
import com.bmx.verde.dto.Card;
import com.bmx.verde.dto.CardReading;
import com.bmx.verde.dto.UpdateInstrumentEventDto;
import com.bmx.verde.dto.UpdateLocationDto;
import com.bmx.verde.model.DispoTest;
import com.bmx.verde.model.Instrument;
import com.bmx.verde.model.InstrumentId;
import com.bmx.verde.model.LocateInstrument;
import com.bmx.verde.model.LocateInstrumentId;
import com.bmx.verde.model.Reading;
import com.bmx.verde.model.TypDispo;
import com.bmx.verde.repository.DispoTestRepository;
import com.bmx.verde.repository.InstrumentRepository;
import com.bmx.verde.repository.LocateInstrumentRepository;
import com.bmx.verde.repository.ReadingRepository;
import com.bmx.verde.repository.TypDispoRepository;
import com.bmx.verde.repository.WorkstationRepository;
import com.bmx.verde.service.RddbWritebackServiceImpl;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RDDBWritebackServiceTests {

    public RDDBWritebackServiceTests() {
    }

    @Mock
    private InstrumentRepository repo;

    @Mock
    private TypDispoRepository typDispoRepo;

    @Mock
    private WorkstationRepository workstationRepo;
    @Mock
    private AmazonSQS sqsClient;

    @InjectMocks
    private RddbWritebackServiceImpl svc;

    @Mock
    private ReadingRepository readingRepo;
    @Mock
    private DispoTestRepository dispoTestRepo;

    @Mock
    private LocateInstrumentRepository locateInstrumentRepository;

    @Mock
    private Gson gson;

    @Mock
    private RddbDao dao;

    // ReceiveMessageRequest receiveMessageRequest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_processCardReadingMessage_barcodeFound() {
        List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();
        com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);

        // Updated message
        String s = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]}";

        Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(64);
        c.setInoculationEpoch("126406462");
        List<CardReading> cardReadings = new ArrayList<CardReading>();
        CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        cr.setWavelength(660);
        int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
                78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102,
                103, 104 };
        cr.setValues(values);
        cr.setWavelength(660);
        cardReadings.add(cr);
        c.setCardReadings(cardReadings);
        when(gson.fromJson(s, Card.class)).thenReturn(c);
        DispoTest dispoTest = new DispoTest();
        dispoTest.setNoDispoTest(13813311);

        when(dispoTestRepo.findByBarcodeNumber(any(String.class))).thenReturn(Optional.of(dispoTest));
        // svc.processCardReadingMessage(s, "");
        svc.processCardReadingMessage(c);
        verify(readingRepo, times(1)).save(any(Reading.class));
    }

    @Test
    public void test_processCardReadingMessage_barcodeFound_104() {
        List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();
        com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);

        // Updated message
        String s = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]}";

        Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(104);
        c.setInoculationEpoch("126406462");
        List<CardReading> cardReadings = new ArrayList<CardReading>();
        CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        cr.setWavelength(660);
        int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
                78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102,
                103, 104 };
        cr.setValues(values);
        cr.setWavelength(660);
        cardReadings.add(cr);
        c.setCardReadings(cardReadings);
        when(gson.fromJson(s, Card.class)).thenReturn(c);
        DispoTest dispoTest = new DispoTest();
        dispoTest.setNoDispoTest(13813311);

        when(dispoTestRepo.findByBarcodeNumber(any(String.class))).thenReturn(Optional.of(dispoTest));
        // svc.processCardReadingMessage(s, "");
        svc.processCardReadingMessage(c);
        verify(readingRepo, times(1)).save(any(Reading.class));
    }

    @Test
    public void test_getReadingCount() {
        List<Reading> readings = new ArrayList<Reading>();
        Reading r = new Reading();
        readings.add(r);
        when(readingRepo.findByIdNoDispoTest(13813311L)).thenReturn(readings);
        // long size = svc.getReadingsCount(13813311L);
        // assertEquals(size, 1L);
    }

    @Test
    public void test_processCardReadingMessage_DipoTestNotFound_TypDspoExists() {
        List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();

        com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);
        TypDispo mockTypDispo = new TypDispo("nameTypDispo");
        mockTypDispo.setNameTypDispo("nameTypDispo");
        when(typDispoRepo.findByNameTypDispo(any(String.class))).thenReturn(Optional.of(mockTypDispo));
        String s = "{\"barcode\":\"1116191404302377\",\"cardName\":\"ZN24\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"Ejected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F65\",\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254],\"wavelength\":660},{\"readingMinute\":126407147,\"opticNumber\":2,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":661},{\"readingMinute\":126407147,\"opticNumber\":3,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":662},{\"readingMinute\":126407147,\"opticNumber\":4,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":560}]}";

        Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(64);
        c.setCardName("ZN24");
        c.setCarouselSlot(1);
        c.setInoculationEpoch("126406462");
        List<CardReading> cardReadings = new ArrayList<CardReading>();
        CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        cr.setWavelength(660);
        int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
                78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102,
                103, 104 };
        cr.setValues(values);
        cardReadings.add(cr);
        c.setCardReadings(cardReadings);
        when(gson.fromJson(s, Card.class)).thenReturn(c);
        when(dispoTestRepo.findByBarcodeNumber(any(String.class))).thenReturn(Optional.empty());
        when(dao.addDispoTest(any(Integer.class), any(String.class), any(Integer.class), any(Integer.class),
                any(String.class), any(Integer.class), any(String.class), any(String.class), any(String.class),
                any(String.class), any(String.class), any(String.class), any(String.class), any(String.class),
                any(String.class))).thenReturn(1);
        // svc.processCardReadingMessage(s, "");
        svc.processCardReadingMessage(c);
        verify(readingRepo, never()).flush();
    }

    @Test
    public void test_processCardReadingMessage_DipoTestNotFound_TypDspoNotExists() {
        List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();
        com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);
        // TypDispo mockTypDispo = new TypDispo("nameTypDispo");
        // mockTypDispo.setNameTypDispo("nameTypDispo");
        when(typDispoRepo.findByNameTypDispo(any(String.class))).thenReturn(Optional.empty());
        String s = "{\"barcode\":\"1116191404302377\",\"cardName\":\"ZN24\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"Ejected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F65\",\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254],\"wavelength\":660},{\"readingMinute\":126407147,\"opticNumber\":2,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":661},{\"readingMinute\":126407147,\"opticNumber\":3,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":662},{\"readingMinute\":126407147,\"opticNumber\":4,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":560}]}";

        Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(64);
        c.setCardName("ZN24");
        c.setCarouselSlot(1);
        c.setCardType("SUSC");
        c.setInoculationEpoch("126406462");
        List<CardReading> cardReadings = new ArrayList<CardReading>();
        CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
                78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102,
                103, 104 };
        cr.setValues(values);
        cr.setWavelength(660);
        cardReadings.add(cr);
        c.setCardReadings(cardReadings);
        when(gson.fromJson(s, Card.class)).thenReturn(c);
        when(dispoTestRepo.findByBarcodeNumber(any(String.class))).thenReturn(Optional.empty());
        when(dao.addDispoTest(any(Integer.class), any(String.class), any(Integer.class), any(Integer.class),
                any(String.class), any(Integer.class), any(String.class), any(String.class), any(String.class),
                any(String.class), any(String.class), any(String.class), any(String.class), any(String.class),
                any(String.class))).thenReturn(1);
        // svc.processCardReadingMessage(s, "");
        svc.processCardReadingMessage(c);
        verify(readingRepo, never()).flush();
    }

    @Test
    public void test_processInstrumentMessage() {
        String s = "{\"noInstr\":\"test229\",\"nameTypInstr\":\"COMPACT\",\"comInstr\":\"Run 569 Diagnosis\",\"instrOpticConfig\":\"\"}";

        when(gson.fromJson(s, com.bmx.verde.dto.Instrument.class))
                .thenReturn(new com.bmx.verde.dto.Instrument("serial", "type", "comments", "DEFAULT_CFG"));
        when(repo.save(any(Instrument.class))).thenReturn(new Instrument());
        // svc.processInstrumentMessage(s, "");
        svc.processInstrumentMessage(new com.bmx.verde.dto.CreateInstrumentDto());
    }

    /**
     * @Test public void test_processCardReadingEmptyCard() { String s =
     * "{\"barcode\":\"1116191404302377\",\"cardName\":\"ZN24\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"Ejected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21
     * 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F65\",\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254],\"wavelength\":660},{\"readingMinute\":126407147,\"opticNumber\":2,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":661},{\"readingMinute\":126407147,\"opticNumber\":3,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":662},{\"readingMinute\":126407147,\"opticNumber\":4,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":560}]}";
     * 
     * when(gson.fromJson(s, Card.class)).thenReturn(null); try {
     * svc.processCardReadingMessage(new Card()); } catch (Exception e) {
     * fail("Should not have thrown any exception"); } }
     */

    @Test
    public void test_processUpdateLocation() {
        final String msg = "{\"serialNumber\":\"99\",\"instrumentType\":\"VITEK\",\"currentLocation\":{\"beginDate\":{\"date\":{\"year\":2019,\"month\":1,\"day\":1},\"time\":{\"hour\":0,\"minute\":0,\"second\":0,\"nano\":0}},\"endDate\":{\"date\":{\"year\":9999,\"month\":12,\"day\":31},\"time\":{\"hour\":0,\"minute\":0,\"second\":0,\"nano\":0}},\"siteCode\":\"STL\"},\"previousLocation\":{\"beginDate\":{\"date\":{\"year\":1990,\"month\":1,\"day\":1},\"time\":{\"hour\":0,\"minute\":0,\"second\":0,\"nano\":0}},\"endDate\":{\"date\":{\"year\":2018,\"month\":12,\"day\":31},\"time\":{\"hour\":23,\"minute\":59,\"second\":59,\"nano\":0}},\"siteCode\":\"UNKE\"}}";
        Gson mapper = new Gson();
        UpdateLocationDto mockDto = mapper.fromJson(msg, UpdateLocationDto.class);
        LocateInstrument mockPrevLoc = new LocateInstrument(mockDto.getSerialNumber(), mockDto.getInstrumentType(),
                mockDto.getPreviousLocation().getBeginDate());
        mockPrevLoc.setSiteCode(mockDto.getPreviousLocation().getSiteCode());
        mockPrevLoc.setEndDate(mockDto.getPreviousLocation().getEndDate());
        mockPrevLoc.setSoftwareVersion("0");
        Optional<LocateInstrument> mockPrev = Optional.of(mockPrevLoc);
        when(locateInstrumentRepository.existsBySerialNumberAndInstrumentType(anyString(), anyString()))
                .thenReturn(true);
        when(locateInstrumentRepository.findById(any(LocateInstrumentId.class))).thenReturn(mockPrev);
        when(locateInstrumentRepository.saveAndFlush(any(LocateInstrument.class))).thenReturn(mockPrevLoc);
        svc.processUpdateLocation(mockDto);
        verify(locateInstrumentRepository, times(1)).existsBySerialNumberAndInstrumentType(anyString(), anyString());
        verify(locateInstrumentRepository, times(1)).findById(any(LocateInstrumentId.class));
        verify(locateInstrumentRepository, times(2)).saveAndFlush(any(LocateInstrument.class));
    }

    @Test
    public void test_processUpdateInstrument_NotFound() {
        UpdateInstrumentEventDto updateEvent = new UpdateInstrumentEventDto();
        when(repo.findById(any(InstrumentId.class))).thenReturn(Optional.empty());
        svc.processUpdateInstrumentMessage(updateEvent);
        verify(repo, never()).save(any(Instrument.class));
    }

    @Test
    public void test_processUpdateInstrument_Found() {
        UpdateInstrumentEventDto updateEvent = new UpdateInstrumentEventDto();
        updateEvent.serialNumber("1234355466");
        updateEvent.setInstrumentType("COMPACT");
        updateEvent.setComment("comment");
        updateEvent.setOpticConfig("DEFAULT_CFG");

        when(repo.findById(any(InstrumentId.class))).thenReturn(Optional.of(new Instrument()));
        svc.processUpdateInstrumentMessage(updateEvent);
        verify(repo, times(1)).save(any(Instrument.class));
    }

}
