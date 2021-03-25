package com.kushnir.taskMySQL.order.service;

import com.kushnir.taskMySQL.order.dto.OrderInputDto;
import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import com.kushnir.taskMySQL.order.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderBaseService {
     Optional<Order> findById(Integer id);

     List<OrderedProductDto> findOrderedProductByOrderId(Integer id);

     List<Order> findAll();

     List<OrderedProductDto> findAllOrders();

     Order createOrder(String createdAt, OrderInputDto orderInputDto);
}
