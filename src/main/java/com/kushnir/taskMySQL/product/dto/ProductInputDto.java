package com.kushnir.taskMySQL.product.dto;

import com.kushnir.taskMySQL.product.ProductStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ProductInputDto {
    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    private ProductStatusEnum status;
}
