package com.bmx.verde.dto;

public class CardReading {

    private Integer readingMinute;
    private Integer opticNumber;
    private int[] values;
    private Integer wavelength;

    public Integer getWavelength() {
        return wavelength;
    }

    public void setWavelength(Integer wavelength) {
        this.wavelength = wavelength;
    }

    public Integer getReadingMinute() {
        return this.readingMinute;
    }

    public void setReadingMinute(Integer readingMinute) {
        this.readingMinute = readingMinute;
    }

    public Integer getOpticNumber() {
        return this.opticNumber;
    }

    public void setOpticNumber(Integer opticNumber) {
        this.opticNumber = opticNumber;
    }

    public int[] getValues() {
        return this.values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
