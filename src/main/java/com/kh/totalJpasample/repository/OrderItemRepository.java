package com.kh.totalJpasample.repository;

import com.kh.totalJpasample.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
