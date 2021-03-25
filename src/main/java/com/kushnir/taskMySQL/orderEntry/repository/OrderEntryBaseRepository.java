package com.kushnir.taskMySQL.orderEntry.repository;

import com.kushnir.taskMySQL.orderEntry.entity.OrderEntry;

import java.util.Optional;

public interface OrderEntryBaseRepository {
    Optional<OrderEntry> findById(Object o);

    OrderEntry save(OrderEntry orderEntry);
}
