package com.kushnir.taskMySQL.product.dto;

import com.kushnir.taskMySQL.product.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductModelConverter {

    ProductOutputDto toDto(Product product);
}
