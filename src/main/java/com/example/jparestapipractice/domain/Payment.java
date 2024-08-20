package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment { // 결제
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id")
    private Long id;
    private Long amount; // 결제 금액
    private LocalDate paymentDate = LocalDate.now(); //결제 날짜
    private String paymentMethod; // 결제 수단
    @Enumerated(EnumType.STRING)
    private PaymentStatus status; //결제 상태

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;



    public void paidStatus() {
        this.status = PaymentStatus.PAID;
    }

    @Builder
    public Payment(Long amount, String paymentMethod, PaymentStatus status, Reservation reservation) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.reservation = reservation;
    }



}
