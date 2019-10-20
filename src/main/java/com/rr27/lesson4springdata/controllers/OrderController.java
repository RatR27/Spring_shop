package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Order;
import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.services.MailService;
import com.rr27.lesson4springdata.services.OrderService;
import com.rr27.lesson4springdata.services.UserService;
import com.rr27.lesson4springdata.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String createOrder(Principal principal,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "address") String address) {
        User user = null;
        if(principal != null){
            //если пользователь аунтефицирован
            user = userService.findByPhone(principal.getName());
        } else {
            //если аноним - но был раньше
            if(userService.isUserExist(phone)){
                user = userService.findByPhone(phone);
            }else {
                //новый гость
                SystemUser systemUser = new SystemUser();
                systemUser.setPhone(phone);
                systemUser.setFirstName(firstName);
                user = userService.save(systemUser);
            }
        }
        Order order = orderService.createOrder(user, phone, address);
        mailService.sendOrderMail(order);
        return "redirect/:shop";
    }

}
