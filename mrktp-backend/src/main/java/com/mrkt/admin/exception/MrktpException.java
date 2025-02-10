package com.mrkt.admin.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MrktpException extends Exception {

    private final MrktpError mrktpError;

    public MrktpException(String message) {
        super(message);
        this.mrktpError = null;
    }

    public MrktpException(MrktpError mrktpError) {
        this.mrktpError = mrktpError;
    }
}
