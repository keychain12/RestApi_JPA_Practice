package com.example.jparestapipractice.controller;

import com.example.jparestapipractice.common.Result;
import com.example.jparestapipractice.domain.Reservation;
import com.example.jparestapipractice.dto.payment.request.PaymentInfo;
import com.example.jparestapipractice.dto.reservation.request.ReservationSaveRequest;
import com.example.jparestapipractice.dto.reservation.response.ReservationResponse;
import com.example.jparestapipractice.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/{reservationId}") //예약 정보 단일 조회
    public ReservationResponse getReservation(@PathVariable Long reservationId) {

        Reservation reservation = reservationService.getReservation(reservationId);
        return ReservationResponse.toResponse(reservation);
    }

    @PostMapping("/{userId}/{flightId}") // 예약 및 결제
    public Result<Long> createReservation(@PathVariable Long userId, @PathVariable Long flightId,
                                    @Valid @RequestBody ReservationSaveRequest request) {

        PaymentInfo paymentInfo = request.getPayment();
        Reservation reservation = request.toEntity();

        Long savedId = reservationService.addReservation(userId, flightId, reservation, paymentInfo);
        return new Result<Long>(savedId);
    }

    @PostMapping("/{reservationId}") // 예약 취소
    public Result reservationRemove(@PathVariable Long reservationId) {
        reservationService.cancel(reservationId);

        return new Result(reservationId);
    }


}
