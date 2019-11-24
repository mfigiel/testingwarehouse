package com.testing.services;

import com.testing.api.mapping.ProductApiProductMapperImpl;
import com.testing.api.resource.ProductApi;
import com.testing.repository.ProductRepository;
import com.testing.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    ProductApiProductMapperImpl productApiProductMapper = new ProductApiProductMapperImpl();

    public List<ProductApi> getProducts() {
        return (List) productRepository.findAll();
    }

    public void addProduct(ProductApi product) {
        productRepository.save(productApiProductMapper.productApiToProductDto(product));
    }

    public ProductApi getProductInformation(long id) {
        return productApiProductMapper.productDtoToProductApi(productRepository.findById(id).get());
    }

    public ProductApi buyProduct(long id) {
        Product product = productRepository.findById(id).get();
        product.setUnitsInStock(product.getUnitsInStock()-1);
        productRepository.save(product);
        return productApiProductMapper.productDtoToProductApi(product);
    }
}
