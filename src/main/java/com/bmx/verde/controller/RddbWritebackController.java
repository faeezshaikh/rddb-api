package com.bmx.verde.controller;

import com.bmx.verde.dto.Card;
import com.bmx.verde.dto.CreateInstrumentDto;
import com.bmx.verde.dto.Event;
import com.bmx.verde.dto.SqsMessage;
import com.bmx.verde.dto.UpdateInstrumentEventDto;
import com.bmx.verde.dto.UpdateLocationDto;
import com.bmx.verde.service.RddbWritebackService;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.dao.DataAccessException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RDDB Write Back SQS Listener and Controller
 */
@RestController
@RequestMapping("api/v1/")
public class RddbWritebackController {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(RddbWritebackController.class);

    /** JSON Mapper for incoming messages */
    @Autowired
    private Gson gson;

    /** Write back service */
    @Autowired
    private RddbWritebackService rddbService;

    /** AWS Simple Queue Service (SQS) location */
    @Value("${sqs.url}")
    private String sqsURL;

    public RddbWritebackController() {
        gson = new Gson();
    }

    @SqsListener(value = "${sqs.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message, @Header("MessageId") String messageId, @Header("ReceiptHandle") String receiptHandle,
            @Header(value = "MD5OfBody", required = false) String mD5OfBody) {
        log.info("Reading message from queue.");
        log.info(" ********** Message Received ************* ");
        log.info("  MessageId:     " + messageId);
        log.debug("  ReceiptHandle: " + receiptHandle);
        log.debug("  MD5OfBody:     " + mD5OfBody);
        log.debug(" Body: " + message);
        SqsMessage sqsMessage = gson.fromJson(message, SqsMessage.class);
        log.info("Parsed message into SQS msg {}", sqsMessage);
        if (sqsMessage.getSubject().equalsIgnoreCase(Event.EVENTS.CREATE_INSTRUMENT.toString())) {
            processCreateInstrument(sqsMessage);
        } else if (sqsMessage.getSubject().equalsIgnoreCase(Event.EVENTS.UPDATE_INSTRUMENT.toString())) {
            processUpdateInstrument(sqsMessage);
        } else if (sqsMessage.getSubject().equalsIgnoreCase(Event.EVENTS.INSTRUMENT_READING.toString())) {
            processInstrumentReading(sqsMessage);
        } else if (sqsMessage.getSubject().equalsIgnoreCase(Event.EVENTS.UPDATE_LOCATION.toString())) {
            processUpdateLocation(sqsMessage);
        }
        log.info("*** SQS Message successfully processed ***");
    }

    /**
     * Process create instrument
     *
     * @param sqsMessage SQS Message received for processing
     */
    public void processCreateInstrument(SqsMessage sqsMessage) {
        CreateInstrumentDto instrumentDto = gson.fromJson(sqsMessage.getMessage(), CreateInstrumentDto.class);
        if (instrumentDto == null) {
            log.info("Processed instrument was empty..Returning");
            return;
        }
        try {
            rddbService.processInstrumentMessage(instrumentDto);
            log.info("Create Instrument msg successfully processed. Serial #: {}, Type: {}",
                    instrumentDto.getSerialNumber(), instrumentDto.getInstrumentType());
        } catch (Exception e) {
            String errorTag = "VITEK_INST_SVC_INST_INSERT_ERROR";
            log.error("{} :: An unexpected exception occured while creating a new instrument. Detail {}. Serial number {}, Type {} ", errorTag,
                    e.getLocalizedMessage(), instrumentDto.getSerialNumber(), instrumentDto.getInstrumentType());
            throw e;
        }
        log.debug("Create Instrument processing complete");
    }

    /**
     * Process update instrument
     *
     * @param sqsMessage SQS Message received for processing
     */
    public void processUpdateInstrument(SqsMessage sqsMessage) {

        UpdateInstrumentEventDto updateInstrumentEventDto = gson.fromJson(sqsMessage.getMessage(), UpdateInstrumentEventDto.class);
        if (updateInstrumentEventDto == null) {
            log.info("Processed update instrument message was empty..Exiting.");
            return;
        }
        try {
            rddbService.processUpdateInstrumentMessage(updateInstrumentEventDto);
            log.info("Update Instrument msg successfully processed. Serial #: {}, Type: {}",
                    updateInstrumentEventDto.getSerialNumber(), updateInstrumentEventDto.getInstrumentType());
        } catch (Exception e) {
            String errorTag = "VITEK_INST_SVC_INST_INSERT_ERROR";
            log.error("{} :: An unexpected exception occured while updating the instrument. Details: {}. Serial number {}, Type {} ", errorTag,
                    e.getLocalizedMessage(), updateInstrumentEventDto.getSerialNumber(), updateInstrumentEventDto.getInstrumentType());
            throw e;
        }
        log.debug("Update Instrument processing complete");
    }

    /**
     * Process instrument reading
     *
     * @param sqsMessage SQS Message received for processing
     */
    public void processInstrumentReading(SqsMessage sqsMessage) {
        Card c = gson.fromJson(sqsMessage.getMessage(), Card.class);
        if (c == null) {
            log.info("Processed card was empty..Returning");
            return;
        }
        try {
            rddbService.processCardReadingMessage(c);
            log.info("Readings message successfully processed. Barcode: {}, Instrument Serial: {}", c.getBarcode(), c.getInstrumentSerialNumber());
        } catch (DataAccessException d) {
            String errorTag = "VITEK_INST_SVC_READING_INSERT_ERROR";
            log.error("{} :: An Exception occurred while inserting card readings. Rolling back transaction. Detail : {}", errorTag,
                    d.getLocalizedMessage());
            try {
                rddbService.updateItgStatusCodeAndLog(c.getBarcode(), c.getInstrumentSerialNumber(), c, d.getLocalizedMessage());
            } catch (Exception e) {
                log.error("{} :: An Exception occured while updating ITG_STATUS_CODE. Rolling back transaction. Detail : {}", errorTag,
                        e.getLocalizedMessage());
            }
        }
        log.debug("Instrument Reading processing complete");
    }

    /**
     * Process update location
     *
     * @param sqsMessage SQS Message received for processing
     */
    public void processUpdateLocation(SqsMessage sqsMessage) {
        UpdateLocationDto updateLocationDto = gson.fromJson(sqsMessage.getMessage(), UpdateLocationDto.class);
        if (updateLocationDto == null) {
            log.debug("Update Location is empty.  Returning.");
            return;
        }
        try {
            log.debug("Processing updated location: {}", updateLocationDto);
            rddbService.processUpdateLocation(updateLocationDto);
        } catch (Exception e) {
            String errorTag = "VITEK_INST_SVC_UPDATE_LOCATION_ERROR";
            log.error("{} :: Exception occurred while updating instrument location. {} - {}", errorTag, updateLocationDto, e.getLocalizedMessage());
            /* TODO rethrow error to prevent record being removed from queue? */
        }
        log.debug("Update location processing complete");
    }

}
