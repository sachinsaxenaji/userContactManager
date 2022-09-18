package com.userContactManager.userContactManager.entity;

import org.hibernate.annotations.GeneratorType;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "orders")

public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myOrderId;

    private String orderId;

    private Integer amount;

    private String receipt;

    private String status;

    @ManyToOne
    private User user;

    private String paymentId;

    public MyOrder(Long myOrderId, String orderId, Integer amount, String receipt, String status, User user, String paymentId) {
        this.myOrderId = myOrderId;
        this.orderId = orderId;
        this.amount = amount;
        this.receipt = receipt;
        this.status = status;
        this.user = user;
        this.paymentId = paymentId;
    }

    public MyOrder() {
    }

    public Long getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(Long myOrderId) {
        this.myOrderId = myOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
