package com.petr.postcode_api.common.exceptions;

public class PostcodeNotFoundException extends RuntimeException {
    public PostcodeNotFoundException(Long id) {
        super("Could not find postcode with id: " + id);
    }
    public PostcodeNotFoundException(String postcode) {
        super("Could not find postcode: " + postcode);
    }
}
