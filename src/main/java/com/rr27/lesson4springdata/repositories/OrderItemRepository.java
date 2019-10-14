package com.rr27.lesson4springdata.repositories;

import com.rr27.lesson4springdata.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
