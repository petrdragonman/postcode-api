package com.petr.postcode_api.common.exceptions;

public class PostcodeNotFoundException extends RuntimeException {
    public PostcodeNotFoundException(Long id) {
        super("Could not found postcode with id: " + id);
    }
}
