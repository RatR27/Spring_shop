package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.services.ProductService;
import com.rr27.lesson4springdata.services.UserService;
import com.rr27.lesson4springdata.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;
    private UserService userService;

    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //Principal - это сущность из Spring Security - UserDetails, который мы настраивали для него
    @GetMapping("")
    public String show(Model model, Principal principal) {
        if (principal != null){
            User user = userService.findByPhone(principal.getName());
            model.addAttribute("phone", user.getPhone());
            model.addAttribute("firstName", user.getFirstName());
        }
        model.addAttribute("items", cart.getItems().values());

        return "cart";
    }

    //метод перенаправит нас на ту страницу откуда мы постучали в него
    //а это страница - /shop у нас, тем самым мы не будем покидать каждый раз каталог
    @GetMapping("/add")
    public void addProductToCart(@RequestParam(name = "id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productService.findById(id);
        cart.addProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }

}
