package com.testing.api.controller;

import com.testing.api.mapping.ProductApiProductMapperImpl;
import com.testing.api.resource.ProductApi;
import com.testing.repository.ProductRepository;
import com.testing.repository.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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

    @RequestMapping(value = "/productpurchased/{id}", method = RequestMethod.GET)
    public ProductApi buyProduct(@PathVariable("id") long id) {
        Product product = productRepository.findById(id).get();
        product.setUnitsInOrder(product.getUnitsInOrder()-1);
        productRepository.save(product);
        return new ProductApi();
    }

}
