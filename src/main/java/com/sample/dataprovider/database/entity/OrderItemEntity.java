package com.sample.dataprovider.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_items", schema = "challenge")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public OrderItemEntity() {}
}