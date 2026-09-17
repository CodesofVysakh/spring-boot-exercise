package com.example.exercise.repository;

import com.example.exercise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔍 Search with pagination
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // 🔗 JOIN query across users + orders
    @Query("SELECT DISTINCT u FROM User u JOIN u.orders o WHERE o.amount > :minAmount")
    List<User> findUsersWithOrdersAbove(@Param("minAmount") BigDecimal minAmount);
}