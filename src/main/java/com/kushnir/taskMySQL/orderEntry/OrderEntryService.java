package com.kushnir.taskMySQL.orderEntry;

import com.kushnir.taskMySQL.order.OrderNotFoundException;
import com.kushnir.taskMySQL.order.OrderService;
import com.kushnir.taskMySQL.orderEntry.dto.OrderEntryInputDto;

import com.kushnir.taskMySQL.product.ProductNotFoundException;
import com.kushnir.taskMySQL.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderEntryService {
    private final OrderEntryRepository orderEntryRepository;
    private final ProductService productService;
    private final OrderService orderService;


    public void createOrderEntry(OrderEntryInputDto orderEntryInputDto, Integer orderId) {
        var order = orderService.findById(orderId);
        var product = productService.findById(orderEntryInputDto.getProductId());
        if (order.isPresent() && product.isPresent()) {

            var orderEntry = new OrderEntry(orderEntryInputDto.getQuantity(), order.get(), product.get());
            orderEntryRepository.save(orderEntry);
        } else if (order.isEmpty()) {
            throw new OrderNotFoundException();
        } else throw new ProductNotFoundException();
    }

    public Optional<OrderEntry> findById(Integer orderId, Integer productId) {
        var order = orderService.findById(orderId);
        var product = productService.findById(productId);
        if (order.isPresent() && product.isPresent()) {
            return orderEntryRepository.findById(new OrderEntry.OrderEntryKey(orderId, productId));
        } else if (order.isEmpty()) {
            throw new OrderNotFoundException();
        } else throw new ProductNotFoundException();

    }

    public void orderEntryUpdate(Integer orderId, Integer productId, Integer quantity) {
        var orderEntry = findById(orderId, productId).orElseThrow(OrderEntryNotFoundException::new);
        orderEntry.setQuantity(quantity);
        orderEntryRepository.save(orderEntry);
    }

}




   


