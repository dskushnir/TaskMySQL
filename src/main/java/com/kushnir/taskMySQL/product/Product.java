package com.kushnir.taskMySQL.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder(toBuilder = true)
@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Product(String name, Integer price, ProductStatusEnum status, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }
}
