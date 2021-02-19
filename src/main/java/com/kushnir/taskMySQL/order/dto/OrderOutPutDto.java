package com.kushnir.taskMySQL.order.dto;

import com.kushnir.taskMySQL.order.OrderStatusEnum;
import lombok.Data;

@Data
public class OrderOutPutDto {
    private Integer id;
    private Integer userId;
    private String createdAt;
    private OrderStatusEnum status;

}
