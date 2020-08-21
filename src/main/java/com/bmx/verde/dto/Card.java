package com.bmx.verde.dto;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String barcode; // This is the natural key
    private String cardName;
    private String cardPhysical;
    private String cardType;
    private String cardStatus;
    private Integer carouselSlot;
    private Integer numberOfWells;
    private String testStatus;
    private String firstReadTimeStamp;
    private String firstReadTimezone;
    private String dilutionType;
    private String mcfarland;
    private String inoculationEpoch;
    private String instrumentSerialNumber;
    private String instrumentType;
    private Instrument instrument;

    private List<CardReading> cardReadings;

    public Card() {
    }

    public Card(Long id, String barcode, String cardName, String cardPhysical, String cardType, String cardStatus,
            Integer carouselSlot, Integer numberOfWells, String testStatus, String firstReadTimeStamp,
            String firstReadTimezone, String dilutionType, String mcfarland, String inoculationEpoch, String instrumentSerialNumber,
            String instrumentType, Instrument instrument, List<CardReading> cardReadings) {
        this.id = id;
        this.barcode = barcode;
        this.cardName = cardName;
        this.cardPhysical = cardPhysical;
        this.cardType = cardType;
        this.cardStatus = cardStatus;
        this.carouselSlot = carouselSlot;
        this.numberOfWells = numberOfWells;
        this.testStatus = testStatus;
        this.firstReadTimeStamp = firstReadTimeStamp;
        this.firstReadTimezone = firstReadTimezone;
        this.dilutionType = dilutionType;
        this.mcfarland = mcfarland;
        this.inoculationEpoch = inoculationEpoch;
        this.instrumentSerialNumber = instrumentSerialNumber;
        this.instrumentType = instrumentType;
        this.instrument = instrument;
        this.cardReadings = cardReadings;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPhysical() {
        return this.cardPhysical;
    }

    public void setCardPhysical(String cardPhysical) {
        this.cardPhysical = cardPhysical;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getCarouselSlot() {
        return this.carouselSlot;
    }

    public void setCarouselSlot(Integer carouselSlot) {
        this.carouselSlot = carouselSlot;
    }

    public Integer getNumberOfWells() {
        return this.numberOfWells;
    }

    public void setNumberOfWells(Integer numberOfWells) {
        this.numberOfWells = numberOfWells;
    }

    public String getTestStatus() {
        return this.testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getFirstReadTimeStamp() {
        return this.firstReadTimeStamp;
    }

    public void setFirstReadTimeStamp(String firstReadTimeStamp) {
        this.firstReadTimeStamp = firstReadTimeStamp;
    }

    public String getFirstReadTimezone() {
        return this.firstReadTimezone;
    }

    public void setFirstReadTimezone(String firstReadTimezone) {
        this.firstReadTimezone = firstReadTimezone;
    }

    public String getDilutionType() {
        return this.dilutionType;
    }

    public void setDilutionType(String dilutionType) {
        this.dilutionType = dilutionType;
    }

    public String getMcfarland() {
        return mcfarland;
    }

    public void setMcfarland(String mcfarland) {
        this.mcfarland = mcfarland;
    }

    public String getInoculationEpoch() {
        return this.inoculationEpoch;
    }

    public void setInoculationEpoch(String inoculationEpoch) {
        this.inoculationEpoch = inoculationEpoch;
    }

    public String getInstrumentSerialNumber() {
        return this.instrumentSerialNumber;
    }

    public void setInstrumentSerialNumber(String instrumentSerialNumber) {
        this.instrumentSerialNumber = instrumentSerialNumber;
    }

    public String getInstrumentType() {
        return this.instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public Instrument getInstrument() {
        return this.instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public List<CardReading> getCardReadings() {
        return this.cardReadings;
    }

    public void setCardReadings(List<CardReading> cardReadings) {
        this.cardReadings = cardReadings;
    }
}
