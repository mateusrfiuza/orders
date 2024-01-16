package com.sample.orders.dataprovider.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders", schema = "challenge")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "seller_id", nullable = false)
    private String sellerID;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSellerID() {
        return sellerID;
    }

    public OrderEntity() {}

}
