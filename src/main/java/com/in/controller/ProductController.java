package com.in.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @GetMapping("/all-products")
    @PreAuthorize("hasRole('USER')")
    public String getAllProducts() {
        return "Getting All Products";
    }

    @GetMapping("/product-by-id/{id}")
    @PreAuthorize("hasRole('USER')")
    public String getProductById(@PathVariable int id) {
        return "Getting Product by ID:" + id;
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct() {
        return "Adding Product";
    }

}
