package com.prosoft.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable String id) {
        // В реальном приложении здесь был бы вызов сервиса/репозитория
        return Map.of(
                "id", id,
                "userId", "123",
                "product", "Product " + id,
                "quantity", 1
        );
    }
}