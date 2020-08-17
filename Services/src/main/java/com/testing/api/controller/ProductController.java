package com.warehouse.api.controller;

import com.warehouse.api.mapping.ProductApiProductMapperImpl;
import com.warehouse.api.resource.ProductApi;
import com.warehouse.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;
    ProductApiProductMapperImpl productApiProductMapper = new ProductApiProductMapperImpl();
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        List products = (List) productRepository.findAll();
        return products;
    }

    @PostMapping("/products")
    void addProduct(@RequestBody ProductApi product) {
        productRepository.save(productApiProductMapper.productApiToProductDto(product));
    }

    @RequestMapping(value = "/productinformation/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        Optional<ProductApi> product = Optional.ofNullable(productApiProductMapper.productDtoToProductApi(productRepository.findById(id)));
        return product.get();
    }

}
