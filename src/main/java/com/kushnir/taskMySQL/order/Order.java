package com.kushnir.taskMySQL.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private Integer id;
    @Column(name = "create_at")
    private String createdAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;


    public Order(Integer id, String createdAt, OrderStatusEnum status) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
    }
}
