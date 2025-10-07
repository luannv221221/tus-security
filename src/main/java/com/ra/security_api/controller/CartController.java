package com.ra.security_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CartController {
    @GetMapping("/api/v1/carts")
    public ResponseEntity<?> getCarts() throws Exception{
        return new ResponseEntity<>("Gio hang", HttpStatus.OK);
    }
}
