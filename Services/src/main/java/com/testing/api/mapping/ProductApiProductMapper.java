package com.warehouse.api.mapping;

import com.warehouse.api.resource.ProductApi;
import com.warehouse.repository.entity.Product;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "ProductApiProductMapper")
public interface ProductApiProductMapper {
    ProductApi productDtoToProductApi(Optional<Product> source);
    Product productApiToProductDto(ProductApi source);
}

