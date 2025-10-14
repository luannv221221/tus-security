package com.ra.security_api.exception;

public class HttpNotFound extends RuntimeException{
    public HttpNotFound(String message){
        super(message);
    }
}
