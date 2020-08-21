package com.bmx.verde.instrument;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.amazonaws.services.sqs.AmazonSQS;
import com.bmx.verde.controller.RddbWritebackController;
import com.bmx.verde.dao.RddbDao;
import com.bmx.verde.dto.Card;
import com.bmx.verde.dto.CardReading;
import com.bmx.verde.dto.CreateInstrumentDto;
import com.bmx.verde.dto.Event;
import com.bmx.verde.dto.SqsMessage;
import com.bmx.verde.dto.UpdateInstrumentEventDto;
import com.bmx.verde.dto.UpdateLocationDto;
import com.bmx.verde.model.DispoTest;
import com.bmx.verde.model.Instrument;
import com.bmx.verde.model.Reading;
import com.bmx.verde.model.TypDispo;
import com.bmx.verde.repository.DispoTestRepository;
import com.bmx.verde.repository.InstrumentRepository;
import com.bmx.verde.repository.ReadingRepository;
import com.bmx.verde.repository.TypDispoRepository;
import com.bmx.verde.repository.WorkstationRepository;
import com.bmx.verde.service.RddbWritebackService;
import com.bmx.verde.service.RddbWritebackServiceImpl;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.util.ReflectionTestUtils;

public class RDDBWritebackControllerTests {

    public RDDBWritebackControllerTests() {
    }

    @InjectMocks
    private RddbWritebackController ctrl;

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
    private Gson gson;

    @Mock
    RddbWritebackService rddbService;

    @Mock
    private RddbDao dao;

    // ReceiveMessageRequest receiveMessageRequest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(ctrl, "sqsURL", "https://sqs.us-east-1.amazonaws.com/635283063535/verde-q");
    }

    @Ignore
    public void test_processCardReadingMessage_barcodeFound() {
        final List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();
        final com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);

        final String s = "{\"barcode\":\"9906191404302376\",\"cardName\":\"ZN22\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"Ejected\",\"carouselSlot\":12,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F65\",\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254],\"wavelength\":660},{\"readingMinute\":126407147,\"opticNumber\":2,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":661},{\"readingMinute\":126407147,\"opticNumber\":3,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":662},{\"readingMinute\":126407147,\"opticNumber\":4,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":560}]}";
        // String s = "{\"barcode\":\"9906191404302376\"}";

        final Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(64);
        final List<CardReading> cardReadings = new ArrayList<CardReading>();
        final CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        final int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76,
                77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101,
                102, 103, 104 };
        cr.setValues(values);
        cardReadings.add(cr);
        c.setCardReadings(cardReadings);
        when(gson.fromJson(s, Card.class)).thenReturn(c);
        final DispoTest dispoTest = new DispoTest();
        dispoTest.setNoDispoTest(13813311);

        when(dispoTestRepo.findByBarcodeNumber(any(String.class))).thenReturn(Optional.of(dispoTest));
        // svc.processCardReadingMessage(s, "");
        svc.processCardReadingMessage(c);
        verify(readingRepo, times(1)).save(any(Reading.class));
    }

    @Test
    public void test_processCardReadingMessage_DipoTestNotFound_TypDspoExists() {
        final List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();

        final com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);
        final TypDispo mockTypDispo = new TypDispo("nameTypDispo");
        mockTypDispo.setNameTypDispo("nameTypDispo");
        when(typDispoRepo.findByNameTypDispo(any(String.class))).thenReturn(Optional.of(mockTypDispo));
        final String s = "{\"barcode\":\"1116191404302377\",\"cardName\":\"ZN24\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"Ejected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F65\",\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254],\"wavelength\":660},{\"readingMinute\":126407147,\"opticNumber\":2,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":661},{\"readingMinute\":126407147,\"opticNumber\":3,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":662},{\"readingMinute\":126407147,\"opticNumber\":4,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":560}]}";

        final Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(64);
        c.setInoculationEpoch("126406462");
        c.setCardName("ZN24");
        c.setCarouselSlot(1);

        final List<CardReading> cardReadings = new ArrayList<CardReading>();
        final CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        cr.setWavelength(660);
        final int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76,
                77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101,
                102, 103, 104 };
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
        final List<com.amazonaws.services.sqs.model.Message> messages = new ArrayList<com.amazonaws.services.sqs.model.Message>();
        final com.amazonaws.services.sqs.model.Message m = new com.amazonaws.services.sqs.model.Message();
        m.setReceiptHandle("receiptHandle");
        messages.add(m);
        // TypDispo mockTypDispo = new TypDispo("nameTypDispo");
        // mockTypDispo.setNameTypDispo("nameTypDispo");
        when(typDispoRepo.findByNameTypDispo(any(String.class))).thenReturn(Optional.empty());
        final String s = "{\"barcode\":\"1116191404302377\",\"cardName\":\"ZN24\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"Ejected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F65\",\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254],\"wavelength\":660},{\"readingMinute\":126407147,\"opticNumber\":2,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":661},{\"readingMinute\":126407147,\"opticNumber\":3,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":662},{\"readingMinute\":126407147,\"opticNumber\":4,\"values\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"wavelength\":560}]}";

        final Card c = new Card();
        c.setBarcode("9906191404302376");
        c.setNumberOfWells(64);
        c.setCardName("ZN24");
        c.setCarouselSlot(1);
        c.setCardType("SUSC");
        c.setInoculationEpoch("126406462");
        final List<CardReading> cardReadings = new ArrayList<CardReading>();
        final CardReading cr = new CardReading();
        // cr.setCardId(1L);
        cr.setOpticNumber(0);
        cr.setReadingMinute(123456);
        cr.setWavelength(660);
        final int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76,
                77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101,
                102, 103, 104 };
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
    public void test_processCreateInstrument() throws Exception {

        final String msgBody =

                "{\"noInstr\":\"test229\",\"nameTypInstr\":\"COMPACT\",\"comInstr\":\"Run 569 Diagnosis\",\"instrOpticConfig\":\"\"}";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.CREATE_INSTRUMENT.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        final com.bmx.verde.dto.CreateInstrumentDto instrumentDto = new com.bmx.verde.dto.CreateInstrumentDto();
        when(gson.fromJson(msgBody, com.bmx.verde.dto.CreateInstrumentDto.class)).thenReturn(instrumentDto);

        Mockito.doNothing().when(rddbService).processInstrumentMessage(any(com.bmx.verde.dto.CreateInstrumentDto.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test(expected = Exception.class)
    public void test_processCreateInstrument_FailCondition() throws Exception {

        final String msgBody =

                "{\"noInstr\":\"test229\",\"nameTypInstr\":\"COMPACT\",\"comInstr\":\"Run 569 Diagnosis\",\"instrOpticConfig\":\"\"}";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.CREATE_INSTRUMENT.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        final com.bmx.verde.dto.CreateInstrumentDto instrumentDto = new com.bmx.verde.dto.CreateInstrumentDto();
        when(gson.fromJson(msgBody, com.bmx.verde.dto.CreateInstrumentDto.class)).thenReturn(instrumentDto);

        Mockito.doThrow(NullPointerException.class).when(rddbService).processInstrumentMessage(any(CreateInstrumentDto.class));

        ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        verify(rddbService, times(1)).processInstrumentMessage(any(CreateInstrumentDto.class));

    }

    @Test
    public void test_processCreateInstrument_shouldNotThrowException() throws Exception {

        final String msgBody =

                "{\"noInstr\":\"test229\",\"nameTypInstr\":\"COMPACT\",\"comInstr\":\"Run 569 Diagnosis\",\"instrOpticConfig\":\"\"}";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.CREATE_INSTRUMENT.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        final com.bmx.verde.dto.Instrument instrumentDto = new com.bmx.verde.dto.Instrument();
        when(gson.fromJson(msgBody, com.bmx.verde.dto.Instrument.class)).thenReturn(instrumentDto);

        Mockito.doThrow(new RuntimeException()).when(rddbService)
                .processInstrumentMessage(any(com.bmx.verde.dto.CreateInstrumentDto.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }

    }

    @Test
    public void test_processInsertReading_InstrumentIsNull() {

        final String msgBody = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408026,\"opticNumber\":1,\"values\":[3877,3387,3327,3293,3257,3338,3394,3358,3653,3224,3281,3191,3229,3164,3255,3368,3831,3339,3254,3315,3303,3223,3331,3395,3819,3433,3398,3237,3349,3271,3345,3388,3848,3364,3298,3282,3215,3279,3361,3408,3437,3302,3319,3244,3190,3213,3255,3326,3263,3216,3247,3181,3077,3290,3266,3326,3550,3318,3331,3182,3233,3265,3319,3363,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408905,\"opticNumber\":1,\"values\":[3864,3420,3293,3271,3229,3316,3379,3383,3777,3247,3238,3144,3192,3136,3243,3386,3890,3327,3209,3252,3236,3166,3297,3388,3822,3422,3362,3209,3304,3244,3326,3395,3853,3372,3282,3263,3191,3258,3347,3420,3474,3290,3293,3217,3154,3175,3228,3331,3295,3215,3218,3155,3065,3279,3245,3339,3606,3339,3320,3182,3235,3255,3322,3422,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]} }";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.CREATE_INSTRUMENT.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        when(gson.fromJson(msgBody, com.bmx.verde.dto.Instrument.class)).thenReturn(null);

        // Mockito.doNothing().when(rddbService).processInstrumentMessage(any(com.bmx.verde.dto.Instrument.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void test_processInsertReading_CardIsNull() {

        final String msgBody = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408026,\"opticNumber\":1,\"values\":[3877,3387,3327,3293,3257,3338,3394,3358,3653,3224,3281,3191,3229,3164,3255,3368,3831,3339,3254,3315,3303,3223,3331,3395,3819,3433,3398,3237,3349,3271,3345,3388,3848,3364,3298,3282,3215,3279,3361,3408,3437,3302,3319,3244,3190,3213,3255,3326,3263,3216,3247,3181,3077,3290,3266,3326,3550,3318,3331,3182,3233,3265,3319,3363,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408905,\"opticNumber\":1,\"values\":[3864,3420,3293,3271,3229,3316,3379,3383,3777,3247,3238,3144,3192,3136,3243,3386,3890,3327,3209,3252,3236,3166,3297,3388,3822,3422,3362,3209,3304,3244,3326,3395,3853,3372,3282,3263,3191,3258,3347,3420,3474,3290,3293,3217,3154,3175,3228,3331,3295,3215,3218,3155,3065,3279,3245,3339,3606,3339,3320,3182,3235,3255,3322,3422,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]} }";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.INSTRUMENT_READING.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void test_processInsertReading() {

        final String msgBody = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408026,\"opticNumber\":1,\"values\":[3877,3387,3327,3293,3257,3338,3394,3358,3653,3224,3281,3191,3229,3164,3255,3368,3831,3339,3254,3315,3303,3223,3331,3395,3819,3433,3398,3237,3349,3271,3345,3388,3848,3364,3298,3282,3215,3279,3361,3408,3437,3302,3319,3244,3190,3213,3255,3326,3263,3216,3247,3181,3077,3290,3266,3326,3550,3318,3331,3182,3233,3265,3319,3363,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408905,\"opticNumber\":1,\"values\":[3864,3420,3293,3271,3229,3316,3379,3383,3777,3247,3238,3144,3192,3136,3243,3386,3890,3327,3209,3252,3236,3166,3297,3388,3822,3422,3362,3209,3304,3244,3326,3395,3853,3372,3282,3263,3191,3258,3347,3420,3474,3290,3293,3217,3154,3175,3228,3331,3295,3215,3218,3155,3065,3279,3245,3339,3606,3339,3320,3182,3235,3255,3322,3422,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]} }";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.INSTRUMENT_READING.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        when(gson.fromJson(msgBody, Card.class)).thenReturn(new Card());

        Mockito.doNothing().when(rddbService).processCardReadingMessage(any(Card.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void test_processInsertReading_handleDataException() {

        final String msgBody = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408026,\"opticNumber\":1,\"values\":[3877,3387,3327,3293,3257,3338,3394,3358,3653,3224,3281,3191,3229,3164,3255,3368,3831,3339,3254,3315,3303,3223,3331,3395,3819,3433,3398,3237,3349,3271,3345,3388,3848,3364,3298,3282,3215,3279,3361,3408,3437,3302,3319,3244,3190,3213,3255,3326,3263,3216,3247,3181,3077,3290,3266,3326,3550,3318,3331,3182,3233,3265,3319,3363,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408905,\"opticNumber\":1,\"values\":[3864,3420,3293,3271,3229,3316,3379,3383,3777,3247,3238,3144,3192,3136,3243,3386,3890,3327,3209,3252,3236,3166,3297,3388,3822,3422,3362,3209,3304,3244,3326,3395,3853,3372,3282,3263,3191,3258,3347,3420,3474,3290,3293,3217,3154,3175,3228,3331,3295,3215,3218,3155,3065,3279,3245,3339,3606,3339,3320,3182,3235,3255,3322,3422,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]} }";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.INSTRUMENT_READING.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        when(gson.fromJson(msgBody, Card.class)).thenReturn(new Card());

        Mockito.doThrow(new DataAccessResourceFailureException("this was the reason")).when(rddbService)
                .processCardReadingMessage(any(Card.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void test_processInsertReading_handleExceptionWhileUpdatingITG() {

        final String msgBody = "{\"barcode\":\"7936191404302376\",\"cardName\":\"AST-GP53\",\"cardPhysical\":\"CARD_64\",\"cardType\":\"AST\",\"cardStatus\":\"NotEjected\",\"carouselSlot\":12,\"numberOfWells\":64,\"testStatus\":\"Final\",\"firstReadTimeStamp\":\"2019-08-21 10:16:28.000000\",\"firstReadTimezone\":\"America/Chicago\",\"dilutionType\":\"Pre-diluted\",\"inoculationEpoch\":\"126406462\",\"instrumentSerialNumber\":\"0000172D9F68\",\"instrumentType\":\"VITEK2XL\",\"instrument\":{\"noInstr\":\"0000172D9F68\",\"nameTypInstr\":\"VITEK2XL\",\"comInstr\":\"Created via Verde during processing card readings.\",\"instrOpticConfig\":\"\"},\"cardReadings\":[{\"readingMinute\":126407147,\"opticNumber\":1,\"values\":[3329,3357,3313,3284,3255,3316,3392,3307,3355,3240,3299,3094,3271,3215,3327,3403,3467,3387,3302,3340,3371,3288,3389,3434,3426,3419,3447,3312,3422,3346,3410,3435,3459,3382,3355,3359,3298,3358,3441,3440,3415,3367,3396,3326,3262,3283,3339,3327,3249,3255,3311,3249,3142,3338,3322,3303,3370,3284,3294,3159,3176,3228,3268,3254,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408026,\"opticNumber\":1,\"values\":[3877,3387,3327,3293,3257,3338,3394,3358,3653,3224,3281,3191,3229,3164,3255,3368,3831,3339,3254,3315,3303,3223,3331,3395,3819,3433,3398,3237,3349,3271,3345,3388,3848,3364,3298,3282,3215,3279,3361,3408,3437,3302,3319,3244,3190,3213,3255,3326,3263,3216,3247,3181,3077,3290,3266,3326,3550,3318,3331,3182,3233,3265,3319,3363,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660},{\"readingMinute\":126408905,\"opticNumber\":1,\"values\":[3864,3420,3293,3271,3229,3316,3379,3383,3777,3247,3238,3144,3192,3136,3243,3386,3890,3327,3209,3252,3236,3166,3297,3388,3822,3422,3362,3209,3304,3244,3326,3395,3853,3372,3282,3263,3191,3258,3347,3420,3474,3290,3293,3217,3154,3175,3228,3331,3295,3215,3218,3155,3065,3279,3245,3339,3606,3339,3320,3182,3235,3255,3322,3422,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"wavelength\":660}]} }";

        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.INSTRUMENT_READING.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        when(gson.fromJson(msgBody, Card.class)).thenReturn(new Card());

        Mockito.doThrow(new RuntimeException()).when(rddbService).updateItgStatusCodeAndLog(any(String.class),
                any(String.class), any(Card.class), any(String.class));

        Mockito.doThrow(new DataAccessResourceFailureException("this was the reason")).when(rddbService)
                .processCardReadingMessage(any(Card.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void test_processInstrumentMessage() {
        final String s = "{\"noInstr\":\"test229\",\"nameTypInstr\":\"COMPACT\",\"comInstr\":\"Run 569 Diagnosis\",\"instrOpticConfig\":\"\"}";

        when(gson.fromJson(s, com.bmx.verde.dto.Instrument.class))
                .thenReturn(new com.bmx.verde.dto.Instrument("serial", "type", "comments", "DEFAULT_CFG"));
        when(repo.save(any(Instrument.class))).thenReturn(new Instrument());
        // svc.processInstrumentMessage(s, "");
        svc.processInstrumentMessage(new com.bmx.verde.dto.CreateInstrumentDto());
    }

    @Test
    public void test_processCardReadingEmptyCard() {
        try {
            svc.processCardReadingMessage(null);
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void test_updateLocation() throws Exception {
        final String msg = "{\"serialNumber\":\"99\",\"instrumentType\":\"VITEK\",\"currentLocation\":{\"beginDate\":{\"date\":{\"year\":2019,\"month\":1,\"day\":1},\"time\":{\"hour\":0,\"minute\":0,\"second\":0,\"nano\":0}},\"endDate\":{\"date\":{\"year\":9999,\"month\":12,\"day\":31},\"time\":{\"hour\":0,\"minute\":0,\"second\":0,\"nano\":0}},\"siteCode\":\"STL\"},\"previousLocation\":{\"beginDate\":{\"date\":{\"year\":1990,\"month\":1,\"day\":1},\"time\":{\"hour\":0,\"minute\":0,\"second\":0,\"nano\":0}},\"endDate\":{\"date\":{\"year\":2018,\"month\":12,\"day\":31},\"time\":{\"hour\":23,\"minute\":59,\"second\":59,\"nano\":0}},\"siteCode\":\"UNKE\"}}";
        final SqsMessage mockMessage = new SqsMessage();
        mockMessage.setMessage(msg);
        mockMessage.setSubject("UPDATE_LOCATION");
        doNothing().when(rddbService).processUpdateLocation(any(UpdateLocationDto.class));
        final Gson mapper = new Gson();
        final UpdateLocationDto mockDto = mapper.fromJson(msg, UpdateLocationDto.class);
        when(gson.fromJson(anyString(), eq(UpdateLocationDto.class))).thenReturn(mockDto);
        ctrl.processUpdateLocation(mockMessage);
        verify(rddbService, times(1)).processUpdateLocation(any(UpdateLocationDto.class));
    }

    @Test
    public void test_updateInstrument_HappyPath() throws Exception {
        final String msg = "{\"serialNumber\":\"test098\",\"instrumentType\":\"VITEK2XL\",\"comment\":\"test update\",\"opticConfig\":\"DEFAULT_CFG\"}";
        final SqsMessage mockMessage = new SqsMessage();
        mockMessage.setMessage(msg);
        mockMessage.setSubject("UPDATE_INSTRUMENT");

        final Gson mapper = new Gson();
        final UpdateInstrumentEventDto mockDto = mapper.fromJson(msg, UpdateInstrumentEventDto.class);
        when(gson.fromJson(anyString(), eq(UpdateInstrumentEventDto.class))).thenReturn(mockDto);

        doNothing().when(rddbService).processUpdateInstrumentMessage(any(UpdateInstrumentEventDto.class));
        ctrl.processUpdateInstrument(mockMessage);
        verify(rddbService, times(1)).processUpdateInstrumentMessage(any(UpdateInstrumentEventDto.class));

    }

    @Test
    public void test_updateInstrument_EventIsNull() throws Exception {
        final String msg = "{\"serialNumber\":\"test098\",\"instrumentType\":\"VITEK2XL\",\"comment\":\"test update\",\"opticConfig\":\"DEFAULT_CFG\"}";
        final SqsMessage mockMessage = new SqsMessage();
        mockMessage.setMessage(msg);
        mockMessage.setSubject("UPDATE_INSTRUMENT");
        when(gson.fromJson(anyString(), eq(UpdateInstrumentEventDto.class))).thenReturn(null);
        doNothing().when(rddbService).processUpdateInstrumentMessage(any(UpdateInstrumentEventDto.class));
        ctrl.processUpdateInstrument(mockMessage);
        verify(rddbService, times(0)).processUpdateInstrumentMessage(any(UpdateInstrumentEventDto.class));
    }

    @Test(expected = Exception.class)
    public void test_updateInstrument_ErrorCondition() throws Exception {
        final String msg = "{\"serialNumber\":\"test098\",\"instrumentType\":\"VITEK2XL\",\"comment\":\"test update\",\"opticConfig\":\"DEFAULT_CFG\"}";
        final SqsMessage mockMessage = new SqsMessage();
        mockMessage.setMessage(msg);
        mockMessage.setSubject("UPDATE_INSTRUMENT");
        final Gson mapper = new Gson();
        final UpdateInstrumentEventDto mockDto = mapper.fromJson(msg, UpdateInstrumentEventDto.class);
        when(gson.fromJson(anyString(), eq(UpdateInstrumentEventDto.class))).thenReturn(mockDto);
        doThrow(NullPointerException.class).when(rddbService).processUpdateInstrumentMessage(any(UpdateInstrumentEventDto.class));
        ctrl.processUpdateInstrument(mockMessage);

        verify(rddbService, times(1)).processUpdateInstrumentMessage(any(UpdateInstrumentEventDto.class));
    }

    @Test
    public void test_updateInstrument_processMessage() throws Exception {

        final String msgBody = "{\"serialNumber\":\"test098\",\"instrumentType\":\"VITEK2XL\",\"comment\":\"test update\",\"opticConfig\":\"DEFAULT_CFG\"}";
        final SqsMessage sqsMsg = new SqsMessage();
        sqsMsg.setSubject(Event.EVENTS.UPDATE_INSTRUMENT.toString());
        sqsMsg.setMessage(msgBody);
        when(gson.fromJson(msgBody, SqsMessage.class)).thenReturn(sqsMsg);

        final com.bmx.verde.dto.Instrument instrumentDto = new com.bmx.verde.dto.Instrument();
        when(gson.fromJson(msgBody, com.bmx.verde.dto.Instrument.class)).thenReturn(instrumentDto);

        Mockito.doNothing().when(rddbService).processInstrumentMessage(any(com.bmx.verde.dto.CreateInstrumentDto.class));

        try {
            ctrl.processMessage(msgBody, "messageId", "receiptHandle", "mD5OfBody");
        } catch (final Exception e) {
            fail("Should not have thrown any exception");
        }

    }

}
