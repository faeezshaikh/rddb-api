package com.bmx.verde.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InstrumentId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name_typ_instr", nullable = false)
    private String nameTypeInstr;

    @Column(name = "no_instr", nullable = false)
    private String noInstr;

    public InstrumentId() {
    }

    public InstrumentId(String nameTypeInstr, String noInstr) {
        this.nameTypeInstr = nameTypeInstr;
        this.noInstr = noInstr;
    }

    public String getNameTypeInstr() {
        return this.nameTypeInstr;
    }

    public void setNameTypeInstr(String nameTypeInstr) {
        this.nameTypeInstr = nameTypeInstr;
    }

    public String getNoInstr() {
        return this.noInstr;
    }

    public void setNoInstr(String noInstr) {
        this.noInstr = noInstr;
    }

    public InstrumentId nameTypeInstr(String nameTypeInstr) {
        this.nameTypeInstr = nameTypeInstr;
        return this;
    }

    public InstrumentId noInstr(String noInstr) {
        this.noInstr = noInstr;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InstrumentId)) {
            return false;
        }
        InstrumentId instrumentId = (InstrumentId) o;
        return Objects.equals(nameTypeInstr, instrumentId.nameTypeInstr)
                && Objects.equals(noInstr, instrumentId.noInstr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTypeInstr, noInstr);
    }

    @Override
    public String toString() {
        return "{" + " nameTypeInstr='" + getNameTypeInstr() + "'" + ", noInstr='" + getNoInstr() + "'" + "}";
    }
}
