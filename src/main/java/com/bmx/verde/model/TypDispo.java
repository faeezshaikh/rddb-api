package com.bmx.verde.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TYP_DISPO")
public class TypDispo {

    @Id
    @Column(name = "NAME_TYP_DISPO", nullable = false)
    private String nameTypDispo;

    public TypDispo() {
    }

    public TypDispo(String nameTypDispo) {
        this.nameTypDispo = nameTypDispo;
    }

    public String getNameTypDispo() {
        return this.nameTypDispo;
    }

    public void setNameTypDispo(String nameTypDispo) {
        this.nameTypDispo = nameTypDispo;
    }

    public TypDispo nameTypDispo(String nameTypDispo) {
        this.nameTypDispo = nameTypDispo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TypDispo)) {
            return false;
        }
        TypDispo typDispo = (TypDispo) o;
        return Objects.equals(nameTypDispo, typDispo.nameTypDispo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameTypDispo);
    }

    @Override
    public String toString() {
        return "{" + " nameTypDispo='" + getNameTypDispo() + "'" + "}";
    }
}
