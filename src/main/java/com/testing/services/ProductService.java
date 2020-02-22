package com.testing.services;

import com.testing.api.mapping.ProductApiProductMapperImpl;
import com.testing.api.resource.ProductApi;
import com.testing.repository.ProductRepository;
import com.testing.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
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

    public ProductApi getProduct(long id) {
        return productApiProductMapper.productDtoToProductApi(Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("product id: " + id))));
    }

    public ProductApi buyProduct(long id) {
        Product product = productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("product id: " + id));
        product.setUnitsInStock(product.getUnitsInStock() - 1);
        productRepository.save(product);
        return productApiProductMapper.productDtoToProductApi(Optional.ofNullable(product));
    }
}
