package com.testing.api.mapping;

import com.testing.api.resource.ProductApi;
import com.testing.repository.entity.Product;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-24T16:26:55+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class ProductApiProductMapperImpl implements ProductApiProductMapper {

    @Override
    public ProductApi productDtoToProductApi(Product source) {
        if ( source == null ) {
            return null;
        }

        ProductApi productApi = new ProductApi();

        productApi.setId( source.getId() );
        productApi.setName( source.getName() );
        productApi.setUnitPrice( source.getUnitPrice() );
        productApi.setDescription( source.getDescription() );
        productApi.setCategory( source.getCategory() );
        productApi.setUnitsInStock( source.getUnitsInStock() );
        productApi.setUnitsInOrder( source.getUnitsInOrder() );

        return productApi;
    }

    @Override
    public Product productApiToProductDto(ProductApi source) {
        if ( source == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( source.getId() );
        product.setName( source.getName() );
        product.setUnitPrice( source.getUnitPrice() );
        product.setDescription( source.getDescription() );
        product.setCategory( source.getCategory() );
        product.setUnitsInStock( source.getUnitsInStock() );
        product.setUnitsInOrder( source.getUnitsInOrder() );

        return product;
    }
}
