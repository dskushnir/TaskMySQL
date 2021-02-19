package com.kushnir.taskMySQL.order;

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
