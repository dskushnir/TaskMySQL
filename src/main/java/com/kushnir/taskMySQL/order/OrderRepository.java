package com.kushnir.taskMySQL.order;

import com.kushnir.taskMySQL.order.dto.OrderedProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT new com.kushnir.taskMySQL.order.dto.OrderedProductDto(o.id,(oE.quantity*p.price),p.name,oE.quantity,o.createdAt) FROM Order AS o INNER JOIN OrderEntry AS oE ON (o.id=oE.id.orderId) INNER JOIN Product AS p ON (p.id=oE.id.productId) WHERE o.id=:id")
    List<OrderedProductDto> findOrderedProductByOrderId(@Param("id") Integer id);

    @Query("SELECT new com.kushnir.taskMySQL.order.dto.OrderedProductDto(o.id,(oE.quantity*p.price),p.name,oE.quantity,o.createdAt) FROM Order AS o INNER JOIN OrderEntry AS oE ON (o.id=oE.id.orderId) INNER JOIN Product AS p ON (p.id=oE.id.productId) ORDER BY o.id")
    List<OrderedProductDto> findAllOrders();
}
