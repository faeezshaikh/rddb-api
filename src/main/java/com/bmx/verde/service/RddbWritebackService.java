package com.bmx.verde.service;

import com.bmx.verde.dto.Card;
import com.bmx.verde.dto.CreateInstrumentDto;
import com.bmx.verde.dto.UpdateInstrumentEventDto;
import com.bmx.verde.dto.UpdateLocationDto;

/**
 * RDDB Write Back Service Interface
 */
public interface RddbWritebackService {

    void processInstrumentMessage(CreateInstrumentDto instrument);

    void processUpdateInstrumentMessage(UpdateInstrumentEventDto updateEvent);

    void processCardReadingMessage(Card card);

    void updateItgStatusCodeAndLog(String barcodeNumber, String serialNumber, Card c, String message);

    /**
     * Processes the update location object and commits it to RDDB
     *
     * @param updateLocationDto Update location message to process
     */
    void processUpdateLocation(UpdateLocationDto updateLocationDto);

}