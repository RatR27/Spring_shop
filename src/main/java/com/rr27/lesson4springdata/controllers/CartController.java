package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.services.ProductService;
import com.rr27.lesson4springdata.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;

    private Cart cart;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String show(Model model, HttpSession httpSession) {
        model.addAttribute("items", cart.getItems().values());
//        List list = Collections.list(httpSession.getAttributeNames());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(httpSession.getAttribute("scopedTarget.cart"));
//        }
//        for (Field f: httpSession.getAttribute("scopedTarget.cart").getClass().getDeclaredFields()){
//            System.out.println(f.getName());
//        }
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
