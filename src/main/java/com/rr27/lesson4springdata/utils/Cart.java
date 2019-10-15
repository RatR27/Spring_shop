package com.rr27.lesson4springdata.utils;

import com.rr27.lesson4springdata.entities.OrderItem;
import com.rr27.lesson4springdata.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class Cart {

    //в будущем можно вренуться к варианту с листом и streamAPI
//    private List<OrderItem> items;

    //но пока для простоты HashMap
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
//       OrderItem item = items.stream().filter(p -> p.getProduct().getId().equals(product.getId())).findFirst()
//               .orElse(null);

        OrderItem item = items.get(product.getId());
        if (item == null){
            item = new OrderItem();
            item.setItemPrice(product.getCost());
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
