package com.petr.postcode_api.common.exceptions;

public class SuburbNotFoundException extends RuntimeException {
    public SuburbNotFoundException(String suburb) {
        super("Could not find suburb: " + suburb);
    }
}
