package com.example.cachedemo.Custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptions {
    // return the 404 NOT FOUND status when this exception is thrown
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        // pass the incoming message to the parent RuntimeException
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

}
