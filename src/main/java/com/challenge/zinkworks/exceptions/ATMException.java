package com.challenge.zinkworks.exceptions;

public class ATMException extends RuntimeException {

    private String code;
    private ATMExcepcionResponse response;

    public ATMException(final String message, final String description, final String code) {
        super(message);
        this.code = code;
        this.response = new ATMExcepcionResponse(message, description, code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public ATMExcepcionResponse getResponse() {
        return response;
    }

    public void setResponse(final ATMExcepcionResponse response) {
        this.response = response;
    }


}
