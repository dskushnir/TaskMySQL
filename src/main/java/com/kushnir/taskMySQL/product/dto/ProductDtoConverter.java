package com.kushnir.taskMySQL.product.dto;

import com.kushnir.taskMySQL.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper
public interface ProductDtoConverter {
    @Mapping(target = "id", ignore = true)
    Product toModel(ProductInputDto productInputDto, LocalDateTime createdAt);

}
