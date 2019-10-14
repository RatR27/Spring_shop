package com.rr27.lesson4springdata.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    public OrderItem() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_price")
    private long itemPrice;

    @Column(name = "total_price")
    private long totalPrice;
}
