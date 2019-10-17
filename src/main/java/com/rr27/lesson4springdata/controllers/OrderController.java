package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Order;
import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.services.MailService;
import com.rr27.lesson4springdata.services.OrderService;
import com.rr27.lesson4springdata.services.UserService;
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
    private MailService mailService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    //Principal - Spring Security интерфейс содержащий инфу о пользователе
    @GetMapping("/create")
    public String createOrder(Principal principal){
        User user = userService.findByUserName(principal.getName());
        Order order = orderService.createOrder(user);
        mailService.sendOrderMail(order);
        return "redirect/:shop";
    }

}
