package com.bmx.verde.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISPO_TEST")
public class DispoTest {

    @Id
    @Column(name = "NO_DISPO_TEST", nullable = false)
    private long noDispoTest;

    @Column(name = "BAR_COD_DISPO_TEST", nullable = false)
    private String barcodeNumber;

    @Column(name = "ITG_STATUS_CODE", nullable = false)
    private String itgStatusCode;

    public DispoTest() {
    }

    public DispoTest(long noDispoTest, String barcodeNumber, String itgStatusCode) {
        this.noDispoTest = noDispoTest;
        this.barcodeNumber = barcodeNumber;
        this.itgStatusCode = itgStatusCode;
    }

    public long getNoDispoTest() {
        return this.noDispoTest;
    }

    public void setNoDispoTest(long noDispoTest) {
        this.noDispoTest = noDispoTest;
    }

    public String getBarcodeNumber() {
        return this.barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getItgStatusCode() {
        return this.itgStatusCode;
    }

    public void setItgStatusCode(String itgStatusCode) {
        this.itgStatusCode = itgStatusCode;
    }

    public DispoTest noDispoTest(long noDispoTest) {
        this.noDispoTest = noDispoTest;
        return this;
    }

    public DispoTest barcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
        return this;
    }

    public DispoTest itgStatusCode(String itgStatusCode) {
        this.itgStatusCode = itgStatusCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DispoTest)) {
            return false;
        }
        DispoTest dispoTest = (DispoTest) o;
        return noDispoTest == dispoTest.noDispoTest && Objects.equals(barcodeNumber, dispoTest.barcodeNumber)
                && Objects.equals(itgStatusCode, dispoTest.itgStatusCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noDispoTest, barcodeNumber, itgStatusCode);
    }

    @Override
    public String toString() {
        return "{" + " noDispoTest='" + getNoDispoTest() + "'" + ", barcodeNumber='" + getBarcodeNumber() + "'"
                + ", itgStatusCode='" + getItgStatusCode() + "'" + "}";
    }
}
