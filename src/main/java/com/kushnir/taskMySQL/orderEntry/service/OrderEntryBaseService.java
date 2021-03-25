package com.kushnir.taskMySQL.orderEntry.service;

import com.kushnir.taskMySQL.orderEntry.dto.OrderEntryInputDto;
import com.kushnir.taskMySQL.orderEntry.entity.OrderEntry;

import java.util.Optional;

public interface OrderEntryBaseService {
    void createOrderEntry(OrderEntryInputDto orderEntryInputDto, Integer orderId);

    Optional<OrderEntry> findById(Integer orderId, Integer productId);

    void orderEntryUpdate(Integer orderId, Integer productId, Integer quantity);
}
