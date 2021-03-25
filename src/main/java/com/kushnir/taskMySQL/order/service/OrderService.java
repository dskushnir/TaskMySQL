package com.kushnir.taskMySQL.order.service;

import com.kushnir.taskMySQL.order.entity.Order;
import com.kushnir.taskMySQL.order.dto.OrderInputDto;
import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import com.kushnir.taskMySQL.order.repository.OrderBaseRepository;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderService implements OrderBaseService {
    private final OrderBaseRepository orderBaseRepository;

    @Override
    public Optional<Order> findById(Integer id) {
        return orderBaseRepository.findById(id);
    }

    @Override
    public List<OrderedProductDto> findOrderedProductByOrderId(Integer id) {
        return orderBaseRepository.findOrderedProductByOrderId(id);
    }

    public List<Order> findAll() {
        return orderBaseRepository.findAll();
    }

    @Override
    public List<OrderedProductDto> findAllOrders() {
        return orderBaseRepository.findAllOrders();
    }

    @Override
    public Order createOrder(String createdAt, OrderInputDto orderInputDto) {
        var order = new Order(orderInputDto.getId(), createdAt, orderInputDto.getStatus());
        return orderBaseRepository.save(order);
    }
}
