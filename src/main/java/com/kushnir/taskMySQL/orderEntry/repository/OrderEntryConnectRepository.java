package com.kushnir.taskMySQL.orderEntry.repository;

import com.kushnir.taskMySQL.orderEntry.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntryConnectRepository extends JpaRepository<OrderEntry, OrderEntry.OrderEntryKey> {
}
