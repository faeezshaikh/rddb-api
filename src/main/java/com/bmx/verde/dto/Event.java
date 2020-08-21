package com.bmx.verde.dto;

public class Event {
    public enum EVENTS {
        CREATE_INSTRUMENT, UPDATE_INSTRUMENT, INSTRUMENT_READING, DELETE_INSTRUMENT, UPDATE_LOCATION
    }

    private EVENTS eventType;
    private String message;

    public EVENTS getEventType() {
        return this.eventType;
    }

    public void setEventType(EVENTS eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
