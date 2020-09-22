package com.axion.test.enums;

public enum ErrorCodes {
    HANDSET_SERVICE_DOWN(424, "Handset Service Exception");

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
