package com.testing.api.controller;

import com.testing.api.resource.ProductApi;
import com.testing.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductApi product) {
        productService.addProduct(product);
    }

    @RequestMapping(value = "/productinformation/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/buyProduct/{id}", method = RequestMethod.GET)
    public ProductApi buyProductInformation(@PathVariable("id") long id) {
        return productService.buyProduct(id);
    }

}
