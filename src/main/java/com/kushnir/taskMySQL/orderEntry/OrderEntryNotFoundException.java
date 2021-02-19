package com.kushnir.taskMySQL.orderEntry;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such a orderEntity")
public class OrderEntryNotFoundException extends RuntimeException {
}
