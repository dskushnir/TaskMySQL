package com.kushnir.taskMySQL.order.entity;

public enum OrderStatusEnum {
    NEW,
    CONFIRMED,
    WAITING_FOR_PAYMENT,
    PAID,
    WAITING_FOR_DELIVERY,
    DELIVERED;

    OrderStatusEnum() {

    }
}
