package com.rr27.lesson4springdata.services;

import com.rr27.lesson4springdata.entities.Order;
import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.repositories.OrderRepository;
import com.rr27.lesson4springdata.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private Cart cart;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCart(OrderRepository orderRepository, Cart cart) {
        this.orderRepository = orderRepository;
        this.cart = cart;
    }

    public Order createOrder(User user, String phone, String address){
        Order order = new Order(user, phone, address);
        cart.getItems().values().stream().forEach(i -> order.addItem(i));
        cart.clear();
        return orderRepository.save(order);
    }


}
