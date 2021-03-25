package com.kushnir.taskMySQL.orderEntry.service;

import com.kushnir.taskMySQL.order.exception.OrderNotFoundException;
import com.kushnir.taskMySQL.order.service.OrderBaseService;

import com.kushnir.taskMySQL.orderEntry.entity.OrderEntry;
import com.kushnir.taskMySQL.orderEntry.exception.OrderEntryNotFoundException;
import com.kushnir.taskMySQL.orderEntry.dto.OrderEntryInputDto;

import com.kushnir.taskMySQL.orderEntry.repository.OrderEntryBaseRepository;

import com.kushnir.taskMySQL.product.exception.ProductNotFoundException;
import com.kushnir.taskMySQL.product.service.ProductBaseService;

import lombok.AllArgsConstructor;


import java.util.Optional;


@AllArgsConstructor
public class OrderEntryService implements OrderEntryBaseService {
    private final OrderEntryBaseRepository orderEntryBaseRepository;
    private final ProductBaseService productBaseService;
    private final OrderBaseService orderBaseService;

    @Override
    public void createOrderEntry(OrderEntryInputDto orderEntryInputDto, Integer orderId) {
        var order = orderBaseService.findById(orderId);
        var product = productBaseService.findById(orderEntryInputDto.getProductId());
        if (order.isPresent() && product.isPresent()) {

            var orderEntry = new OrderEntry(orderEntryInputDto.getQuantity(), order.get(), product.get());
            orderEntryBaseRepository.save(orderEntry);
        } else if (order.isEmpty()) {
            throw new OrderNotFoundException();
        } else throw new ProductNotFoundException();
    }

    @Override
    public Optional<OrderEntry> findById(Integer orderId, Integer productId) {
        var order = orderBaseService.findById(orderId);
        var product = productBaseService.findById(productId);
        if (order.isPresent() && product.isPresent()) {
            return orderEntryBaseRepository.findById(new OrderEntry.OrderEntryKey(orderId, productId));
        } else if (order.isEmpty()) {
            throw new OrderNotFoundException();
        } else throw new ProductNotFoundException();

    }

    @Override
    public void orderEntryUpdate(Integer orderId, Integer productId, Integer quantity) {
        var orderEntry = findById(orderId, productId).orElseThrow(OrderEntryNotFoundException::new);
        orderEntry.setQuantity(quantity);
        orderEntryBaseRepository.save(orderEntry);
    }

}




   


