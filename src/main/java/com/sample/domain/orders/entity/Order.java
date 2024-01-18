package com.sample.domain.orders.entity;

import com.sample.domain.orders.usecase.commands.CreateOrderCommand;

import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private OrderStatus status;
    private String customerId;
    private String sellerID;
    private String currency;
    private Double totalPrice;
    private final List<OrderItem> orderItems;

    public Order(final OrderStatus status,
                 final String customerId,
                 final String currency,
                 final String sellerID,
                 final List<OrderItem> orderItems) {
        this.status = status;
        this.currency = currency;
        this.customerId = customerId;
        this.sellerID = sellerID;
        this.orderItems = orderItems;
        this.totalPrice = this.calculateTotalPrice();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        this.totalPrice = calculateTotalPrice();
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        this.totalPrice = calculateTotalPrice();
    }

    public Double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice();
        }
        return totalPrice;
    }

    public static Order fromCreateOrderCommand(CreateOrderCommand command) {
        List<OrderItem> orderItems = command.items()
                .stream()
                .map(OrderItem::fromItemCreateOrderCommand)
                .collect(Collectors.toList());

        return new Order(
                OrderStatus.CREATED,
                command.customerId(),
                command.currency(),
                command.sellerId(),
                orderItems
        );
    }
}
