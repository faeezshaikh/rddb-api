package com.bmx.verde.dto;

import java.util.Objects;

public class UpdateInstrumentEventDto {

    private String serialNumber;
    private String instrumentType;
    private String comment;
    private String opticConfig;

    public UpdateInstrumentEventDto() {
    }

    public UpdateInstrumentEventDto(String serialNumber, String instrumentType, String comment, String opticConfig) {
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

    public UpdateInstrumentEventDto serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public UpdateInstrumentEventDto instrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
        return this;
    }

    public UpdateInstrumentEventDto comment(String comment) {
        this.comment = comment;
        return this;
    }

    public UpdateInstrumentEventDto opticConfig(String opticConfig) {
        this.opticConfig = opticConfig;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UpdateInstrumentEventDto)) {
            return false;
        }
        UpdateInstrumentEventDto updateInstrumentEventDto = (UpdateInstrumentEventDto) o;
        return Objects.equals(serialNumber, updateInstrumentEventDto.serialNumber)
                && Objects.equals(instrumentType, updateInstrumentEventDto.instrumentType)
                && Objects.equals(comment, updateInstrumentEventDto.comment) && Objects.equals(opticConfig, updateInstrumentEventDto.opticConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, instrumentType, comment, opticConfig);
    }

    @Override
    public String toString() {
        return "{" +
                " serialNumber='" + getSerialNumber() + "'" +
                ", instrumentType='" + getInstrumentType() + "'" +
                ", comment='" + getComment() + "'" +
                ", opticConfig='" + getOpticConfig() + "'" +
                "}";
    }

}
