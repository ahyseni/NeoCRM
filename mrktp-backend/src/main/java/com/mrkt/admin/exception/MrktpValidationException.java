package com.mrkt.admin.exception;

public class MrktpValidationException extends MrktpException {
    MrktpError mrktpError;
    String message;

    public MrktpValidationException(String message) {
        super(message);
    }


}
