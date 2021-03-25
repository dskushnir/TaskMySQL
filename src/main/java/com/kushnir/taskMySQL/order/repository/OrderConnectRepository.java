package com.kushnir.taskMySQL.order.repository;

import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import com.kushnir.taskMySQL.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderConnectRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT new com.kushnir.taskMySQL.order.dto.OrderedProductDto(o.id,(oE.quantity*p.price),p.name,oE.quantity,o.createdAt) FROM Order AS o INNER JOIN OrderEntry AS oE ON (o.id=oE.id.orderId) INNER JOIN Product AS p ON (p.id=oE.id.productId) WHERE o.id=:id")
    List<OrderedProductDto> findOrderedProductByOrderId(@Param("id") Integer id);

    @Query("SELECT new com.kushnir.taskMySQL.order.dto.OrderedProductDto(o.id,(oE.quantity*p.price),p.name,oE.quantity,o.createdAt) FROM Order AS o INNER JOIN OrderEntry AS oE ON (o.id=oE.id.orderId) INNER JOIN Product AS p ON (p.id=oE.id.productId) ORDER BY o.id")
    List<OrderedProductDto> findAllOrders();
}
