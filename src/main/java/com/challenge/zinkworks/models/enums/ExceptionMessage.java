package com.challenge.zinkworks.models.enums;

public enum ExceptionMessage {
    INCORRECT_PIN_CODE("001"),
    INCORRECT_PIN("INCORRECT PIN, TRY AGAIN"),
    INSUFFICIENT_FOUND_CODE("002"),
    INSUFFICIENT_FOUND("INSUFFICIENT FOUND. TRY A SMALLER AMOUNT"),
    INCORRECT_AMOUNT_CODE("003"),
    INCORRECT_AMOUNT("THE AMOUNT REQUESTED CANNOT BE DISPENSED, TRY AGAIN WITH OTHER AMOUNT"),
    LIMIT_EXCEEDED_CODE("004"),
    LIMIT_EXCEEDED("WITHDRAWAL LIMIT EXCEEDED"),
    NO_BILLS_ATM_CODE("005"),
    NO_BILLS_ATM("THERE ARE NO BILLS IN THIS ATM");





    private String message;

    public String getMessage() {
        return this.message;
    }

    ExceptionMessage(final String message) {
        this.message = message;
    }
}
