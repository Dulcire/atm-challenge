package com.challenge.zinkworks.exceptions;

/**
 * Class to get the ATM Errors reponse.
 */
public class ATMExcepcionResponse {

    private String message;
    private String description;
    private String code;

    public ATMExcepcionResponse(final String message, final String description) {
        this.message = message;
        this.description = description;
    }

    public ATMExcepcionResponse(final String message, final String description, final String code) {
        this.message = message;
        this.description = description;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
