package com.bmx.verde.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INSTR")
public class Instrument {

    @EmbeddedId
    private InstrumentId id;

    @Column(name = "com_instr", nullable = false)
    private String comInstr;

    @Column(name = "workstation_id", nullable = false)
    private long workstationId;

    @Column(name = "instr_optic_config", nullable = false)
    private String instrOpticConfig;

    public Instrument() {
    }

    public Instrument(InstrumentId id, String comInstr, long workstationId, String instrOpticConfig) {
        this.id = id;
        this.comInstr = comInstr;
        this.workstationId = workstationId;
        this.instrOpticConfig = instrOpticConfig;
    }

    public InstrumentId getId() {
        return this.id;
    }

    public void setId(InstrumentId id) {
        this.id = id;
    }

    public String getComInstr() {
        return this.comInstr;
    }

    public void setComInstr(String comInstr) {
        this.comInstr = comInstr;
    }

    public long getWorkstationId() {
        return this.workstationId;
    }

    public void setWorkstationId(long workstationId) {
        this.workstationId = workstationId;
    }

    public String getInstrOpticConfig() {
        return this.instrOpticConfig;
    }

    public void setInstrOpticConfig(String instrOpticConfig) {
        this.instrOpticConfig = instrOpticConfig;
    }

    public Instrument id(InstrumentId id) {
        this.id = id;
        return this;
    }

    public Instrument comInstr(String comInstr) {
        this.comInstr = comInstr;
        return this;
    }

    public Instrument workstationId(long workstationId) {
        this.workstationId = workstationId;
        return this;
    }

    public Instrument instrOpticConfig(String instrOpticConfig) {
        this.instrOpticConfig = instrOpticConfig;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Instrument)) {
            return false;
        }
        Instrument instrument = (Instrument) o;
        return Objects.equals(id, instrument.id) && Objects.equals(comInstr, instrument.comInstr)
                && workstationId == instrument.workstationId
                && Objects.equals(instrOpticConfig, instrument.instrOpticConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comInstr, workstationId, instrOpticConfig);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", comInstr='" + getComInstr() + "'" + ", workstationId='"
                + getWorkstationId() + "'" + ", instrOpticConfig='" + getInstrOpticConfig() + "'" + "}";
    }
}
