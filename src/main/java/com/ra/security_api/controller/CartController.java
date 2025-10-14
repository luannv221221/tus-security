package com.ra.security_api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CartController {
    @GetMapping("/api/v1/carts")
    public ResponseEntity<?> getCarts() throws Exception{
        return new ResponseEntity<>("Gio hang", HttpStatus.OK);
    }
    @GetMapping("/api/v1.carts/{id}")
    public ResponseEntity<?> getCart(
            @Parameter(description = "id cart detail",example = "1")
            @PathVariable String id) throws Exception{
        return new ResponseEntity<>("Chi tiet gio hang", HttpStatus.OK);
    }
}
