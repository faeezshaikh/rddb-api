package com.bmx.verde.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents the LocateInstrument unique composite key. See LocateInstrument object for further details.
 */
public class LocateInstrumentId implements Serializable {

    /** Instrument Number */
    private String serialNumber;

    /** Instrument Type */
    private String instrumentType;

    /** The Beginning Date for the instrument at the specified location/siteId */
    private LocalDateTime beginDate;

    /**
     * Empty Constructor
     */
    public LocateInstrumentId() {
    }

    /**
     * Full constructor to populate this class
     *
     * @param serialNumber instrument number
     * @param instrumentType instrument type
     * @param beginDate beginning date at specified location
     */
    public LocateInstrumentId(String serialNumber, String instrumentType, LocalDateTime beginDate) {
        this.serialNumber = serialNumber;
        this.instrumentType = instrumentType;
        this.beginDate = beginDate;
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

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LocateInstrumentId)) {
            return false;
        }
        final LocateInstrumentId other = (LocateInstrumentId) o;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(serialNumber, other.serialNumber);
        eb.append(instrumentType, other.instrumentType);
        eb.append(beginDate, other.beginDate);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(serialNumber);
        hcb.append(instrumentType);
        hcb.append(beginDate);
        return hcb.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        tsb.append(serialNumber);
        tsb.append(instrumentType);
        tsb.append(beginDate);
        return tsb.build();
    }

}
