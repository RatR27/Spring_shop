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
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;

    private Cart cart;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //в модель добавили все продукты из списка
    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("items", cart.getItems());
        return "cart";
    }

    //метод перенаправит нас на ту страницу откуда мы постучали в него
    //а это страница - /shop у нас, тем самым мы не будем покидать каждый раз каталог
    @GetMapping("/add/{id}")
    public void addProductToCart(@RequestParam(name = "id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productService.findById(id);
        cart.addProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }

}
