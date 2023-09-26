package com.veera.controller;

import com.veera.entity.Product;
import com.veera.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value="/product")
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping(value = "/product")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping(value="/cartvirtual")
    public String addToCartVirtual(@RequestParam("userId")String userId,
                            @RequestParam("productId")Long productId) {
        System.out.println(Thread.currentThread().getName());
        return productService.addProductToCartVirtual(userId, productId);
    }

    @PostMapping(value="/cart")
    public String addToCart(@RequestParam("userId")String userId,
                            @RequestParam("productId")Long productId) {
        System.out.println(Thread.currentThread().getName());
        return productService.addProductToCart(userId, productId);
    }
}
