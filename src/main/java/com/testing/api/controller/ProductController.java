package com.testing.api.controller;

import com.testing.api.resource.ProductApi;
import com.testing.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    ProductService productService = new ProductService();

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    void addProduct(@RequestBody ProductApi product) {
        productService.addProduct(product);
    }

    @RequestMapping(value = "/productinformation/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        return productService.getProductInformation(id);
    }

}
