package com.example.jparestapipractice.dto.reservation.request;

import com.example.jparestapipractice.domain.Payment;
import com.example.jparestapipractice.domain.Reservation;
import com.example.jparestapipractice.domain.Status;
import com.example.jparestapipractice.dto.payment.request.PaymentInfo;
import com.example.jparestapipractice.dto.payment.response.PaymentResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationSaveRequest {

    @NotNull
    private LocalDate reservationDate;

    @NotNull
    private PaymentInfo payment;


    public Reservation toEntity() {
        return Reservation.builder()
                .reservationDate(this.reservationDate)
                .status(Status.CONFIRMED)
                .build();
    }


}
