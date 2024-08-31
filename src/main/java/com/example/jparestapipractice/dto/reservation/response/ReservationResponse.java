package com.example.jparestapipractice.dto.reservation.response;

import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.domain.Reservation;
import com.example.jparestapipractice.dto.flight.response.FlightResponse;
import com.example.jparestapipractice.dto.user.response.UserResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationResponse {


    private LocalDate reservationDate;  // 예약 날짜

    private String status;  // 예약 상태

    private FlightResponse flight;  // 예약한 항공편


    public static ReservationResponse toResponse(Reservation reservation) {
        UserResponse user = Objects.isNull(reservation.getUser()) ?
                null : UserResponse.toResponse(reservation.getUser());
        FlightResponse flight = Objects.isNull(reservation.getFlight()) ?
                null : FlightResponse.toResponse(reservation.getFlight());

        return ReservationResponse.builder()
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus().name())
                .flight(flight)
                .build();


    }



}
