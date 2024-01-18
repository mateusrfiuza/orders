package com.sample.domain.orders.entity;

import com.sample.domain.orders.usecase.commands.ItemCreateOrderCommand;

public class OrderItem {

    private String productId;
    private Double price;

    public static OrderItem fromItemCreateOrderCommand(ItemCreateOrderCommand command) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(command.productId());
        orderItem.setPrice(1.0);
        return orderItem;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}