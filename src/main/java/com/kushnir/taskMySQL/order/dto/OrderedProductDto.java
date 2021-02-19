package com.kushnir.taskMySQL.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDto {
    private Integer orderId;
    private Integer productsTotalPrice;
    private String productName;
    private Integer productsQuantity;
    private String orderCreatedDate;
}
