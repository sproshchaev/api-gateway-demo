package com.prosoft.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable String id) {
        // В реальном приложении здесь был бы вызов сервиса/репозитория
        return Map.of(
                "id", id,
                "name", "User " + id,
                "email", "user" + id + "@example.com"
        );
    }
}