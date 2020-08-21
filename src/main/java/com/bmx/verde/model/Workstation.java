package com.bmx.verde.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workstation")
public class Workstation {

    @Id
    @Column(unique = true, updatable = false, name = "workstation_id", nullable = false)
    private Integer workstationId;

    @Column(name = "software_release_id", nullable = false)
    private Long softwareReleaseId;

    @Column(name = "workstation_name", nullable = false)
    private String workstationName;

    public Workstation() {
    }

    public Workstation(Integer workstationId, Long softwareReleaseId, String workstationName) {
        this.workstationId = workstationId;
        this.softwareReleaseId = softwareReleaseId;
        this.workstationName = workstationName;
    }

    public Integer getWorkstationId() {
        return this.workstationId;
    }

    public void setWorkstationId(Integer workstationId) {
        this.workstationId = workstationId;
    }

    public Long getSoftwareReleaseId() {
        return this.softwareReleaseId;
    }

    public void setSoftwareReleaseId(Long softwareReleaseId) {
        this.softwareReleaseId = softwareReleaseId;
    }

    public String getWorkstationName() {
        return this.workstationName;
    }

    public void setWorkstationName(String workstationName) {
        this.workstationName = workstationName;
    }

    public Workstation workstationId(Integer workstationId) {
        this.workstationId = workstationId;
        return this;
    }

    public Workstation softwareReleaseId(Long softwareReleaseId) {
        this.softwareReleaseId = softwareReleaseId;
        return this;
    }

    public Workstation workstationName(String workstationName) {
        this.workstationName = workstationName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Workstation)) {
            return false;
        }
        Workstation workstation = (Workstation) o;
        return Objects.equals(workstationId, workstation.workstationId)
                && Objects.equals(softwareReleaseId, workstation.softwareReleaseId)
                && Objects.equals(workstationName, workstation.workstationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workstationId, softwareReleaseId, workstationName);
    }

    @Override
    public String toString() {
        return "{" + " workstationId='" + getWorkstationId() + "'" + ", softwareReleaseId='" + getSoftwareReleaseId()
                + "'" + ", workstationName='" + getWorkstationName() + "'" + "}";
    }
}
