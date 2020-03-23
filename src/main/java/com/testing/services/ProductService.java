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
        return productApiProductMapper.clientListToClientApiList((List) productRepository.findAll());
    }

    public ProductApi addProduct(ProductApi product) {
        Product productDto = productApiProductMapper.productApiToProductDto(product);
        productRepository.save(productDto);
        return productApiProductMapper.productDtoToProductApi(productDto);
    }

    public ProductApi getProduct(long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("client id: " + id)));

        return productApiProductMapper.productDtoToProductApi(product.get());
    }

    public Product getProductDto(long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("client id: " + id)));

        return product.get();
    }

    public ProductApi buyProduct(long id) {
        Product product = getProductDto(id);
        product.setUnitsInStock(product.getUnitsInStock() - 1);
        productRepository.save(product);
        return productApiProductMapper.productDtoToProductApi(product);
    }
}
