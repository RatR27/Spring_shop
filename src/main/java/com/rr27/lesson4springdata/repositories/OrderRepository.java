package com.rr27.lesson4springdata.repositories;

import com.rr27.lesson4springdata.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
