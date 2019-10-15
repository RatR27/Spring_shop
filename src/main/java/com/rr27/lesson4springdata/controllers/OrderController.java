package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Order;
import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.repositories.UserRepository;
import com.rr27.lesson4springdata.services.OrderService;
import com.rr27.lesson4springdata.services.UserService;
import com.rr27.lesson4springdata.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    //Principal - Spring Security интерфейс содержащий инфу о пользователе
    @GetMapping("/create")
    public String createOrder(Principal principal){
        User user = userService.findByUserName(principal.getName());
        orderService.createOrder(user);
        return "redirect/:shop";
    }

}
