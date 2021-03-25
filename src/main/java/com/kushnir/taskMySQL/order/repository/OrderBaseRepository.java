package com.kushnir.taskMySQL.order.repository;

import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import com.kushnir.taskMySQL.order.entity.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderBaseRepository {
    List<OrderedProductDto> findOrderedProductByOrderId(@Param("id") Integer id);

    List<OrderedProductDto> findAllOrders();

    Optional<Order> findById(Integer id);

    List<Order> findAll();

    Order save(Order order);
}
