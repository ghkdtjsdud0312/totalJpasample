package com.kh.totalJpasample.repository;

import com.kh.totalJpasample.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
