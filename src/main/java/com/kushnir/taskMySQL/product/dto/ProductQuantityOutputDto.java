package com.kushnir.taskMySQL.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityOutputDto {
    private String nameOfProduct;
    private Long quantityOfProduct;
}
