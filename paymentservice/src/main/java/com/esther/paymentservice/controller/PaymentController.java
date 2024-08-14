package com.esther.paymentservice.controller;

import com.esther.paymentservice.entity.Payment;
import com.esther.paymentservice.payload.PaymentDto;
import com.esther.paymentservice.payload.PaymentMapper;
import com.esther.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> submitPayment(@RequestBody PaymentDto paymentDto) {
        Payment payment = PaymentMapper.fromDto(paymentDto);
        Payment submittedPayment = paymentService.submitPayment(payment);
        return ResponseEntity.ok(PaymentMapper.toDto(submittedPayment));
    }

    @PatchMapping("/{id}/refund")
    public ResponseEntity<PaymentDto> reversePayment(@PathVariable Long id) {
        Payment refundedPayment = paymentService.reversePayment(id);
        return ResponseEntity.ok(PaymentMapper.toDto(refundedPayment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getPayment(id);
        return ResponseEntity.ok(PaymentMapper.toDto(payment));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePaymentStatus(@PathVariable Long id, @RequestBody String status) {
        Payment updatedPayment = paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(PaymentMapper.toDto(updatedPayment));
    }
}

