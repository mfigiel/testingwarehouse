package com.warehouse.api.controller;

import com.warehouse.api.resource.ProductApi;
import com.warehouse.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    public ProductApi addProduct(@RequestBody ProductApi product) {
        return productService.addProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/buyProduct/{id}", method = RequestMethod.GET)
    public ProductApi buyProductInformation(@PathVariable("id") long id) {
        return productService.buyProduct(id);
    }

}
