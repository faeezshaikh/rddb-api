package com.bmx.verde.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CreateInstrumentDto {

    private String serialNumber;

    private String instrumentType;

    private String comment;

    private String opticConfig;

    public CreateInstrumentDto() {
    }

    public CreateInstrumentDto(String serialNumber, String instrumentType, String comment, String opticConfig) {
        this.serialNumber = serialNumber;
        this.instrumentType = instrumentType;
        this.comment = comment;
        this.opticConfig = opticConfig;
    }

    public CreateInstrumentDto(String serialNumber, String instrumentType, String comment, String opticConfig, String siteCode, String beginDate,
            String endDate) {
        this.serialNumber = serialNumber;
        this.instrumentType = instrumentType;
        this.comment = comment;
        this.opticConfig = opticConfig;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInstrumentType() {
        return this.instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOpticConfig() {
        return this.opticConfig;
    }

    public void setOpticConfig(String opticConfig) {
        this.opticConfig = opticConfig;
    }

    public CreateInstrumentDto serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public CreateInstrumentDto instrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
        return this;
    }

    public CreateInstrumentDto comment(String comment) {
        this.comment = comment;
        return this;
    }

    public CreateInstrumentDto opticConfig(String opticConfig) {
        this.opticConfig = opticConfig;
        return this;
    }

}
