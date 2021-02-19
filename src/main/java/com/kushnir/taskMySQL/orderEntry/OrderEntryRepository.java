package com.kushnir.taskMySQL.orderEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntry, OrderEntry.OrderEntryKey> {
}
