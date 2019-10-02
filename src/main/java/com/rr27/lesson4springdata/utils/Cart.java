package com.rr27.lesson4springdata.utils;

import com.rr27.lesson4springdata.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class Cart {

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    //метод выполнится после создания Bean'а
    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
    }

    public void addProduct(Product product){
        productList.add(product);
    }

}
