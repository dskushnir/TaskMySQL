package com.kushnir.taskMySQL.orderEntry;

import com.kushnir.taskMySQL.order.Order;
import com.kushnir.taskMySQL.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "order_items")
public class OrderEntry {
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderEntryKey implements Serializable {
        @Column(name = "order_id")
        private Integer orderId;
        @Column(name = "product_id")
        private Integer productId;
    }

    @EmbeddedId
    private OrderEntryKey id = new OrderEntryKey();
    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    protected Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    protected Product product;

    public OrderEntry(Integer quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
        this.id.orderId = order.getId();
        this.id.productId = product.getId();

    }
}
