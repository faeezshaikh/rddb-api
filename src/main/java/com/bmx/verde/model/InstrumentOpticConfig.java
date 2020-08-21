package com.bmx.verde.model;

import java.util.Objects;

public class InstrumentOpticConfig {

    private String instrOpticConfig;
    private String description;
    private String flagDefault;

    public InstrumentOpticConfig() {
    }

    public InstrumentOpticConfig(String instrOpticConfig, String description, String flagDefault) {
        this.instrOpticConfig = instrOpticConfig;
        this.description = description;
        this.flagDefault = flagDefault;
    }

    public String getInstrOpticConfig() {
        return this.instrOpticConfig;
    }

    public void setInstrOpticConfig(String instrOpticConfig) {
        this.instrOpticConfig = instrOpticConfig;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlagDefault() {
        return this.flagDefault;
    }

    public void setFlagDefault(String flagDefault) {
        this.flagDefault = flagDefault;
    }

    public InstrumentOpticConfig instrOpticConfig(String instrOpticConfig) {
        this.instrOpticConfig = instrOpticConfig;
        return this;
    }

    public InstrumentOpticConfig description(String description) {
        this.description = description;
        return this;
    }

    public InstrumentOpticConfig flagDefault(String flagDefault) {
        this.flagDefault = flagDefault;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InstrumentOpticConfig)) {
            return false;
        }
        InstrumentOpticConfig instrumentOpticConfig = (InstrumentOpticConfig) o;
        return Objects.equals(instrOpticConfig, instrumentOpticConfig.instrOpticConfig)
                && Objects.equals(description, instrumentOpticConfig.description)
                && Objects.equals(flagDefault, instrumentOpticConfig.flagDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrOpticConfig, description, flagDefault);
    }

    @Override
    public String toString() {
        return "{" + " instrOpticConfig='" + getInstrOpticConfig() + "'" + ", description='" + getDescription() + "'"
                + ", flagDefault='" + getFlagDefault() + "'" + "}";
    }
}
