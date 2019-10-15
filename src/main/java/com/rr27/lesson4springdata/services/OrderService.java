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
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void createOrder(User user){
        Order order = new Order(user);
        cart.getItems().values().stream().forEach(i -> order.addItem(i));
        cart.clear();
        orderRepository.save(order);
    }


}
