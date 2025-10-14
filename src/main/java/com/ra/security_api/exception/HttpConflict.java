package com.ra.security_api.exception;

public class HttpConflict extends RuntimeException {
    public HttpConflict(String message) {
        super(message);
    }
}
