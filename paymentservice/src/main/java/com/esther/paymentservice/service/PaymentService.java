package com.esther.paymentservice.service;

import com.esther.paymentservice.dao.PaymentRepository;
import com.esther.paymentservice.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    public Payment submitPayment(Payment payment) {
        // Check if a payment with the same transactionId exists
        paymentRepository.findByTransactionId(payment.getTransactionId())
                .ifPresent(p -> {
                    throw new IllegalStateException("Payment transaction is already processed.");
                });

        payment.setStatus("COMPLETED");  // Assuming instant payment for simplicity
        Payment savedPayment = paymentRepository.save(payment);

        // Publish to Kafka
        kafkaTemplate.send("payment-success", savedPayment);
        return savedPayment;
    }

    public Payment reversePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found"));

        if (!payment.getStatus().equals("COMPLETED")) {
            throw new IllegalStateException("Only completed payments can be refunded.");
        }

        payment.setStatus("REFUNDED");
        Payment updatedPayment = paymentRepository.save(payment);

        // Publish to Kafka
        kafkaTemplate.send("payment-refunded", updatedPayment);
        return updatedPayment;
    }

    public Payment updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found"));
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found"));
    }
}

