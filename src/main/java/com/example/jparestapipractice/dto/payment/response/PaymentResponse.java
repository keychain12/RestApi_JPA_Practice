package com.example.jparestapipractice.dto.payment.response;

import com.example.jparestapipractice.domain.Payment;
import com.example.jparestapipractice.domain.PaymentStatus;
import com.example.jparestapipractice.dto.reservation.response.ReservationResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentResponse {


    private Long amount;
    private LocalDate paymentDate; //결제 날짜
    private PaymentStatus status; //결제 상태
    private String paymentMethod; // 결제 방식
    private ReservationResponse reservation; // 예약

    public static PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .status(PaymentStatus.PAID)
                .paymentMethod(payment.getPaymentMethod())
                .reservation(ReservationResponse.toResponse(payment.getReservation()))
                .build();
    }

}
