package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.services.ProductService;
import com.rr27.lesson4springdata.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * required = false - если данного параметра в url не будет, то мы не схватим Exception
     *
     */
    @RequestMapping()
    public String shop(Model model, HttpServletRequest request, HttpServletResponse response,
                       @CookieValue(value = "page_size", required = false) Integer pageSize,
                       @RequestParam(name = "pageNumber", required = false) Integer pageNumber
//                               @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        //выводит url с которого совершался последний переход
//        System.out.println(request.getHeader("referer"));

        ProductFilter productFilter = new ProductFilter(request);
        if(pageNumber == null){
            pageNumber = 1;
        }
        //TODO! добавить выпадающий список в фильтр формы с кол-вом э-тов на странице
        if(pageSize == null){
            pageSize = 5;
            response.addCookie(new Cookie("page_size", String.valueOf(pageSize)));
        }
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", productFilter.getFiltersBuilder());
        Page<Product> page = productService.findAllByPagingAndFiltering(productFilter.getSpecification(), PageRequest.of(pageNumber-1, pageSize, Sort.Direction.ASC,"id"));
        model.addAttribute("page", page);
        return "shop";
    }
}