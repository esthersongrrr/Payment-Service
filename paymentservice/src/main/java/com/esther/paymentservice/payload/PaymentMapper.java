package com.esther.paymentservice.payload;

import com.esther.paymentservice.entity.Payment;

public class PaymentMapper {
    public static PaymentDto toDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getTransactionId()
        );
    }

    public static Payment fromDto(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setId(paymentDto.getId());
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setCurrency(paymentDto.getCurrency());
        payment.setStatus(paymentDto.getStatus());
        payment.setTransactionId(paymentDto.getTransactionId());
        return payment;
    }
}

