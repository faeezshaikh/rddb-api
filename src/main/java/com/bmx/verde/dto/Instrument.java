package com.bmx.verde.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Instrument {

    private String serialNumber;

    private String instrumentType;

    private String comment;

    private String opticConfig;

    public Instrument() {
    }

    public Instrument(String noInstr, String nameTypInstr, String comInstr, String instrOpticConfig) {
        this.serialNumber = noInstr;
        this.instrumentType = nameTypInstr;
        this.comment = comInstr;
        this.opticConfig = instrOpticConfig;
    }

    public Instrument noInstr(String noInstr) {
        this.serialNumber = noInstr;
        return this;
    }

    public Instrument nameTypInstr(String nameTypInstr) {
        this.instrumentType = nameTypInstr;
        return this;
    }

    public Instrument comInstr(String comInstr) {
        this.comment = comInstr;
        return this;
    }

    public Instrument instrOpticConfig(String instrOpticConfig) {
        this.opticConfig = instrOpticConfig;
        return this;
    }

    public String getNoInstr() {
        return this.serialNumber;
    }

    public void setNoInstr(String noInstr) {
        this.serialNumber = noInstr;
    }

    public String getNameTypInstr() {
        return this.instrumentType;
    }

    public void setNameTypInstr(String nameTypInstr) {
        this.instrumentType = nameTypInstr;
    }

    public String getComInstr() {
        return this.comment;
    }

    public void setComInstr(String comInstr) {
        this.comment = comInstr;
    }

    public String getInstrOpticConfig() {
        return this.opticConfig;
    }

    public void setInstrOpticConfig(String instrOpticConfig) {
        this.opticConfig = instrOpticConfig;
    }
}
