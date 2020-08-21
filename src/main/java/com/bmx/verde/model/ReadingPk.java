package com.bmx.verde.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReadingPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "NO_DISPO_TEST", nullable = false)
    private long noDispoTest;

    @Column(name = "WAVELENGTH", nullable = false)
    private int wavelength;

    @Column(name = "TPS_RESUL_NET", nullable = false)
    private long tpsResultNet;

    public ReadingPk() {
    }

    public ReadingPk(long noDispoTest, int wavelength, long tpsResultNet) {
        this.noDispoTest = noDispoTest;
        this.wavelength = wavelength;
        this.tpsResultNet = tpsResultNet;
    }

    public long getNoDispoTest() {
        return this.noDispoTest;
    }

    public void setNoDispoTest(long noDispoTest) {
        this.noDispoTest = noDispoTest;
    }

    public int getWavelength() {
        return this.wavelength;
    }

    public void setWavelength(int wavelength) {
        this.wavelength = wavelength;
    }

    public long getTpsResultNet() {
        return this.tpsResultNet;
    }

    public void setTpsResultNet(long tpsResultNet) {
        this.tpsResultNet = tpsResultNet;
    }

    public ReadingPk noDispoTest(long noDispoTest) {
        this.noDispoTest = noDispoTest;
        return this;
    }

    public ReadingPk wavelength(int wavelength) {
        this.wavelength = wavelength;
        return this;
    }

    public ReadingPk tpsResultNet(long tpsResultNet) {
        this.tpsResultNet = tpsResultNet;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ReadingPk)) {
            return false;
        }
        ReadingPk readingPk = (ReadingPk) o;
        return noDispoTest == readingPk.noDispoTest && wavelength == readingPk.wavelength
                && tpsResultNet == readingPk.tpsResultNet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noDispoTest, wavelength, tpsResultNet);
    }

    @Override
    public String toString() {
        return "{" + " noDispoTest='" + getNoDispoTest() + "'" + ", wavelength='" + getWavelength() + "'"
                + ", tpsResultNet='" + getTpsResultNet() + "'" + "}";
    }
}
