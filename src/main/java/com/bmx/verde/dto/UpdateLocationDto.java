package com.bmx.verde.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Update Location Data Transfer Object. Used to pass the updated location to external service.
 */
public class UpdateLocationDto {

    /** Instrument Serial Number */
    private String serialNumber;

    /** Instrument Type */
    private String instrumentType;

    /** Current (New) Location with start and end dates */
    private UpdateLocationSiteDto currentLocation;

    /** Previous (Old) Location with start and end dates */
    private UpdateLocationSiteDto previousLocation;

    /**
     * Empty Constructor
     */
    public UpdateLocationDto() {
    }

    /**
     * Basic Constructor
     *
     * @param serialNumber serial number
     * @param instrumentType instrument type
     */
    public UpdateLocationDto(String serialNumber, String instrumentType) {
        this.serialNumber = serialNumber;
        this.instrumentType = instrumentType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public UpdateLocationSiteDto getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(UpdateLocationSiteDto currentLocation) {
        this.currentLocation = currentLocation;
    }

    public UpdateLocationSiteDto getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(UpdateLocationSiteDto previousLocation) {
        this.previousLocation = previousLocation;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UpdateLocationDto)) {
            return false;
        }
        final UpdateLocationDto other = (UpdateLocationDto) o;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(serialNumber, other.serialNumber);
        eb.append(instrumentType, other.instrumentType);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(serialNumber);
        hcb.append(instrumentType);
        return hcb.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        tsb.append(serialNumber);
        tsb.append(instrumentType);
        return tsb.build();
    }

}
