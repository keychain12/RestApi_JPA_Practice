package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Payment { // 결제
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;
    private BigDecimal amount; // 결제 금액
    private LocalDateTime paymentDate; //결제 날짜
    @Enumerated(EnumType.STRING)
    private PaymentStatus status; //결제 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
