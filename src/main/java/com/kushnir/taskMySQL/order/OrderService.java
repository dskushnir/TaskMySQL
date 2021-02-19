package com.kushnir.taskMySQL.order;

import com.kushnir.taskMySQL.order.dto.OrderInputDto;
import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public List<OrderedProductDto> findOrderedProductByOrderId(Integer id) {
        return orderRepository.findOrderedProductByOrderId(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderedProductDto> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    public Order createOrder(String createdAt, OrderInputDto orderInputDto) {
        var order = new Order(orderInputDto.getId(), createdAt, orderInputDto.getStatus());
        return orderRepository.save(order);
    }
}
