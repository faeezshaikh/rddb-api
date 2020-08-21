package com.bmx.verde.dto;

public class SqsMessage {
    // Note: Camel casing wont work. This class is being used to deserialize via
    // GSON.
    private String Subject;
    private String Message;

    public String getSubject() {
        return this.Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
