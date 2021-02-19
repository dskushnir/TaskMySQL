package com.kushnir.taskMySQL.orderEntry.dto;

import lombok.Data;

@Data
public class OrderEntryInputDtoForUpdate {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
}
