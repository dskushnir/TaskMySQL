package com.kushnir.taskMySQL.orderEntry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderEntryInputDto {

    private Integer productId;
    private Integer quantity;
}
