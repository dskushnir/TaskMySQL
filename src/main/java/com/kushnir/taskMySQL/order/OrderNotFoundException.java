package com.kushnir.taskMySQL.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such a order")
public class OrderNotFoundException extends RuntimeException {
}
