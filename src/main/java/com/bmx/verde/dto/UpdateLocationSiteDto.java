package com.bmx.verde.dto;

import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Update Location Site Data Transfer Object. Used to pass the updated location site to external service.
 */
public class UpdateLocationSiteDto {

    /** Beginning date for location */
    private LocalDateTime beginDate;

    /** Ending date for location */
    private LocalDateTime endDate;

    /** Location identifier */
    private String siteCode;

    /**
     * Empty Constructor
     */
    public UpdateLocationSiteDto() {
    }

    /**
     * Full Constructor with start date, end date, and site id
     *
     * @param beginDate beginning date
     * @param endDate ending date
     * @param siteCode site identifier
     */
    public UpdateLocationSiteDto(LocalDateTime beginDate, LocalDateTime endDate, String siteCode) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.siteCode = siteCode;
    }

    public LocalDateTime getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getSiteCode() {
        return this.siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UpdateLocationSiteDto)) {
            return false;
        }
        final UpdateLocationSiteDto other = (UpdateLocationSiteDto) o;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(beginDate, other.beginDate);
        eb.append(endDate, other.endDate);
        eb.append(siteCode, other.siteCode);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(beginDate);
        hcb.append(endDate);
        hcb.append(siteCode);
        return hcb.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        tsb.append(beginDate);
        tsb.append(endDate);
        tsb.append(siteCode);
        return tsb.build();
    }

}
