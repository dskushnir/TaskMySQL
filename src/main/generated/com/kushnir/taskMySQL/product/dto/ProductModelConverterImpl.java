package com.kushnir.taskMySQL.product.dto;

import com.kushnir.taskMySQL.product.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-18T21:28:25+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ProductModelConverterImpl implements ProductModelConverter {

    @Override
    public ProductOutputDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductOutputDto productOutputDto = new ProductOutputDto();

        productOutputDto.setName( product.getName() );
        productOutputDto.setPrice( product.getPrice() );
        productOutputDto.setStatus( product.getStatus() );

        return productOutputDto;
    }
}
