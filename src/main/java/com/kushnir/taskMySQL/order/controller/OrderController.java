package com.kushnir.taskMySQL.order.controller;

import com.kushnir.taskMySQL.order.exception.OrderNotFoundException;
import com.kushnir.taskMySQL.order.service.OrderBaseService;
import com.kushnir.taskMySQL.order.dto.OrderInputDto;
import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import com.kushnir.taskMySQL.orderEntry.service.OrderEntryBaseService;
import com.kushnir.taskMySQL.orderEntry.dto.OrderEntryInputDtoForUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class OrderController {
    private final OrderBaseService orderBaseService;
    private final OrderEntryBaseService orderEntryBaseService;
    private final Clock clock;
    private final UriComponentsBuilder uriBuilder;

    public OrderController(OrderBaseService orderBaseService, OrderEntryBaseService orderEntryBaseService, Clock clock) {
        this.orderBaseService = orderBaseService;
        this.orderEntryBaseService = orderEntryBaseService;
        this.clock = clock;
        uriBuilder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8080")
                .path("/orders/{id}");
    }

    @GetMapping("/ordersById/{id}")
    public List<OrderedProductDto> findOrderedProductByOrderId(@PathVariable Integer id) {
        var orderedProducts = orderBaseService.findOrderedProductByOrderId(id);
        if (orderedProducts.size() == 0) {
            throw new OrderNotFoundException();
        }
        return orderedProducts;
    }

    @GetMapping("/orders")
    public List<OrderedProductDto> findAllOrders() {
        var orders = orderBaseService.findAllOrders();
        if (orders.size() == 0) {
            throw new OrderNotFoundException();
        }
        return orders;
    }

    @PostMapping("/ordersCreate")
    public ResponseEntity<Object> createOrder(@RequestBody OrderInputDto orderInputDto) {
        var created = orderBaseService.createOrder(LocalDateTime.now(clock).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), orderInputDto);
        orderInputDto.getOrderEntryList().forEach(e -> orderEntryBaseService.createOrderEntry(e, orderInputDto.getId()));
        return ResponseEntity.created(uriBuilder.build(created.getId())).build();
    }

    @PutMapping("/ordersEntityUpdate")
    public ResponseEntity<Object> updateOrderEntry(@RequestBody OrderEntryInputDtoForUpdate forUpdate) {
        orderEntryBaseService.orderEntryUpdate(forUpdate.getOrderId(), forUpdate.getProductId(), forUpdate.getQuantity());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
