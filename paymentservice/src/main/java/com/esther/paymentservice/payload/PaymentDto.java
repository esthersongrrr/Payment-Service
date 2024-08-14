package com.esther.paymentservice.payload;

import java.math.BigDecimal;

public class PaymentDto {
    private Long id;
    private String orderId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String transactionId;

    // Constructors, Getters, and Setters
    public PaymentDto() {}

    public PaymentDto(Long id, String orderId, BigDecimal amount, String currency, String status, String transactionId) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.transactionId = transactionId;
    }

    // Standard getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

