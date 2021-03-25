package com.kushnir.taskMySQL.order.dto;

import com.kushnir.taskMySQL.order.entity.OrderStatusEnum;
import com.kushnir.taskMySQL.orderEntry.dto.OrderEntryInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInputDto {
    private Integer id;
    private OrderStatusEnum status;
    private List<OrderEntryInputDto> orderEntryList;


}
