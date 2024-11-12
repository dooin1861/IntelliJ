package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
@Controller
public class ProductsController {

    @GetMapping
    public String showProductsPage() {
        return "products/products";  // "products.html"을 렌더링
    }

    @GetMapping("/core")
    public String Core() {
        return "products/core";
    }

    @GetMapping("/s2")
    public String S2() {
        return "products/s2";
    }

}
