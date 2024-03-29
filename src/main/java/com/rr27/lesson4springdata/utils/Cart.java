package com.rr27.lesson4springdata.utils;

import com.rr27.lesson4springdata.entities.OrderItem;
import com.rr27.lesson4springdata.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class Cart {
    //key (Long) - id продукта, по которму различаем OrderItem
    //value - OrderItem
    private Map<Long, OrderItem> items;
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<Long, OrderItem> getItems() {
        return items;
    }

    //метод выполнится после создания Bean'а
    @PostConstruct
    public void init(){
        items = new HashMap<>();
    }

    public void addProduct(Product product){
        OrderItem item = items.get(product.getId());
        //если в корзине товара такого типа - нет, то создаем его
        if (item == null){
            item = new OrderItem();
            item.setItemPrice(product.getPrice());
            item.setProduct(product);
            item.setQuantity(0);
        }
        item.setQuantity(item.getQuantity()+1);
        item.setTotalPrice(item.getItemPrice().multiply(new BigDecimal(item.getQuantity())));
        items.put(product.getId(), item);
        recalculate();
    }

    public void removeItem(Product product){
        items.remove(product.getId());
        recalculate();
    }

    //перерасчет стоимости oi при удалении товара и добавлении
    private void recalculate(){
        totalPrice = new BigDecimal(0);
        items.values().stream().forEach(oi -> totalPrice = totalPrice.add(oi.getTotalPrice()));
    }

    public void clear(){
        items.clear();
        totalPrice = new BigDecimal(0);
    }
}
