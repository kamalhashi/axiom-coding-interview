package com.axion.test.exception;

import com.axion.test.enums.ErrorCodes;

public class HandsetServiceUnavailableException extends RuntimeException {
    private  Integer errorCode;

    public HandsetServiceUnavailableException(String message){
        super(message);
    }
    public HandsetServiceUnavailableException(String message, Throwable cause){
        super(message, cause);
    }
    public HandsetServiceUnavailableException(String message, Throwable cause, ErrorCodes errorCode ){
        super(message, cause);
        this.errorCode = errorCode.getCode();
    }

    public HandsetServiceUnavailableException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }



}
