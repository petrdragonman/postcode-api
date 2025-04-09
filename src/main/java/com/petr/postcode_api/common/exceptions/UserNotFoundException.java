package com.petr.postcode_api.common.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not found user with id: " + id);
    }
}
