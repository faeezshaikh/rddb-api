package com.bmx.verde.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Instrument location object
 */
@Entity
@Table(name = "locate_instr")
@IdClass(LocateInstrumentId.class)
public class LocateInstrument {

    /** Instrument Serial Number */
    @Id
    @Column(name = "no_instr", columnDefinition = "character varying(12) not null")
    private String serialNumber;

    /** Instrument Type */
    @Id
    @Column(name = "name_typ_instr", columnDefinition = "character varying(10) not null")
    private String instrumentType;

    /** Beginning Date for location */
    @Id
    @Column(name = "begin_date", columnDefinition = "timestamp without time zone not null")
    private LocalDateTime beginDate;

    /** Ending Date for location */
    @Column(name = "end_date", columnDefinition = "timestamp without time zone not null")
    private LocalDateTime endDate;

    /** Location Identifier */
    @Column(name = "gold_test_site_id", columnDefinition = "character varying(6) not null")
    private String siteCode;

    /** Software Version - Obsolete RDDB Field. Default to zero. Integration testing proved that this field needs to exists to avoid errors. */
    @Column(name = "version_itg_software", columnDefinition = "character varying(10) not null")
    private String softwareVersion = "0";

    /**
     * Empty Constructor
     */
    public LocateInstrument() {
    }

    /**
     * Unique identifier constructor consisting of serial number, type, and start date/
     *
     * @param serialNumber serial number
     * @param instrumentType type of instrument
     * @param beginDate starting date
     */
    public LocateInstrument(String serialNumber, String instrumentType, LocalDateTime beginDate) {
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LocateInstrument)) {
            return false;
        }
        final LocateInstrument other = (LocateInstrument) o;
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
        tsb.append(endDate);
        tsb.append(siteCode);
        return tsb.build();
    }

}
