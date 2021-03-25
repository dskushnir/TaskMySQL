package com.kushnir.taskMySQL.order.repository;

import com.kushnir.taskMySQL.order.entity.Order;
import com.kushnir.taskMySQL.order.dto.OrderedProductDto;


import java.util.List;
import java.util.Optional;


public class OrderRepository implements OrderBaseRepository {
    OrderConnectRepository connectRepository;

    @Override
    public List<OrderedProductDto> findOrderedProductByOrderId(Integer id) {
        return connectRepository.findOrderedProductByOrderId(id);
    }

    @Override
    public List<OrderedProductDto> findAllOrders() {
        return connectRepository.findAllOrders();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return connectRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return connectRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return connectRepository.save(order);
    }


}
