package com.warehouse.services;

import com.warehouse.api.mapping.ProductApiProductMapper;
import com.warehouse.api.resource.ProductApi;
import com.warehouse.logging.Exceptions.SoldOutException;
import com.warehouse.repository.ProductRepository;
import com.warehouse.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductApiProductMapper productApiProductMapper;

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
                .orElseThrow(() -> new EntityNotFoundException("product id: " + id)));

        return productApiProductMapper.productDtoToProductApi(product.get());
    }

    public Product getProductDto(long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("product id: " + id)));

        return product.get();
    }

    public ProductApi buyProduct(long id) {
        Product product = getProductDto(id);

        if (product.isSoldOut()) {
            throw new SoldOutException("product id: ".concat(String.valueOf(id)));
        }
        product.setUnitsInStock(product.getUnitsInStock() - 1);
        product.setUnitsInOrder(product.getUnitsInOrder() + 1);
        if (product.getUnitsInStock() == 0) {
            product.setSoldOut(true);
        }
        productRepository.save(product);
        return productApiProductMapper.productDtoToProductApi(product);
    }
}
