package com.kushnir.taskMySQL.product.dto;

import com.kushnir.taskMySQL.product.ProductStatusEnum;
import lombok.Data;

@Data
public class ProductOutputDto {
    private String name;
    private Integer price;
    private ProductStatusEnum status;

}
