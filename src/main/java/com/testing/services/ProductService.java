package com.testing.services;

import com.testing.api.mapping.ProductApiProductMapperImpl;
import com.testing.api.resource.ProductApi;
import com.testing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    ProductApiProductMapperImpl productApiProductMapper = new ProductApiProductMapperImpl();

    public List<ProductApi> getProducts() {
        return (List) productRepository.findAll();
    }

    public void addProduct(ProductApi product) {
        productRepository.save(productApiProductMapper.productApiToProductDto(product));
    }

    public ProductApi getProductInformation(long id) {
        Optional<ProductApi> product = Optional.ofNullable(productApiProductMapper.productDtoToProductApi(productRepository.findById(id)));
        return product.get();
    }
}
