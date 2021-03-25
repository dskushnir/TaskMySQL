package com.kushnir.taskMySQL.product.dto;

import com.kushnir.taskMySQL.product.entity.Product;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-18T21:28:25+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ProductDtoConverterImpl implements ProductDtoConverter {

    @Override
    public Product toModel(ProductInputDto productInputDto, LocalDateTime createdAt) {
        if ( productInputDto == null && createdAt == null ) {
            return null;
        }

        Product product = new Product();

        if ( productInputDto != null ) {
            product.setName( productInputDto.getName() );
            product.setPrice( productInputDto.getPrice() );
            product.setStatus( productInputDto.getStatus() );
        }
        if ( createdAt != null ) {
            product.setCreatedAt( createdAt );
        }

        return product;
    }
}
