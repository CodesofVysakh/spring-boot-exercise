package com.example.exercise.service;

import com.example.exercise.entity.Order;
import com.example.exercise.entity.User;
import com.example.exercise.repository.OrderRepository;
import com.example.exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order placeOrder(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Order order = Order.builder()
                .amount(amount)
                .user(user)
                .build();

        return orderRepository.save(order);
    }
}