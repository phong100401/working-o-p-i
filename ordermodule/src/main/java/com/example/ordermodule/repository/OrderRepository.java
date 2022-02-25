package com.example.ordermodule.repository;

import com.example.ordermodule.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query("SELECT o FROM Order o WHERE o.userId = 451691 ORDER BY o.id DESC")
    List<Order> findOrderByUserId(int userId);
}
