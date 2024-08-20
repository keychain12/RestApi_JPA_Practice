package com.example.jparestapipractice.controller;

import com.example.jparestapipractice.domain.Payment;
import com.example.jparestapipractice.dto.payment.response.PaymentResponse;
import com.example.jparestapipractice.repository.PaymentRepository;
import com.example.jparestapipractice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{paymentId}") // 단일 결제 내역 조회
    public PaymentResponse getPayment(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPayment(paymentId);
        return PaymentResponse.toResponse(payment);
    }
}
