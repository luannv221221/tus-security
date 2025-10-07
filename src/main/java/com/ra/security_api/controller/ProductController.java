package com.ra.security_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
//    @PostAuthorize("hasAuthority('USER')")
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() throws Exception{
        return new ResponseEntity<>("Danh sach san pham", HttpStatus.OK);
    }
//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test")
    public ResponseEntity<?> getTest() throws Exception{
        return new ResponseEntity<>("Danh sach san pham", HttpStatus.OK);
    }
}
