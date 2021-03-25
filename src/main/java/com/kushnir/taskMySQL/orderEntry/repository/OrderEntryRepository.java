package com.kushnir.taskMySQL.orderEntry.repository;

import com.kushnir.taskMySQL.orderEntry.entity.OrderEntry;


import java.util.Optional;


public class OrderEntryRepository implements OrderEntryBaseRepository {
    OrderEntryConnectRepository orderEntryConnectRepository;

    @Override
    public Optional<OrderEntry> findById(Object o) {
        return orderEntryConnectRepository.findById((OrderEntry.OrderEntryKey) o);
    }

    @Override
    public OrderEntry save(OrderEntry orderEntry) {
        return orderEntryConnectRepository.save(orderEntry);
    }
}
