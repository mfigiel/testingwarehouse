package com.testing.api.mapping;

import com.testing.api.resource.ProductApi;
import com.testing.repository.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "ProductApiProductMapper")
public interface ProductApiProductMapper {
    ProductApi productDtoToProductApi(Product source);
    Product productApiToProductDto(ProductApi source);
    List<ProductApi> clientListToClientApiList(List<Product> source);
}

