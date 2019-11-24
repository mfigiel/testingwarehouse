package com.testing.api.controller;

import com.testing.api.resource.ProductApi;
import com.testing.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    void addProduct(@RequestBody ProductApi product) {
        productService.addProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        return productService.getProductInformation(id);
    }

    @RequestMapping(value = "/buyProduct/{id}", method = RequestMethod.GET)
    public ProductApi buyProductInformation(@PathVariable("id") long id) {
        return productService.buyProduct(id);
    }


}
