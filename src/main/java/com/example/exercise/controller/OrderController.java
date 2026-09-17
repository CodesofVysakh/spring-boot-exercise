package com.example.exercise.controller;

import com.example.exercise.entity.Order;
import com.example.exercise.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // POST — place an order
    @PostMapping
    public Order placeOrder(@RequestParam Long userId,
                            @RequestParam BigDecimal amount) {
        return orderService.placeOrder(userId, amount);
    }
}