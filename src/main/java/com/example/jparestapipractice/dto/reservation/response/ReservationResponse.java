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

    private Long id;

    private LocalDate reservationDate;

    private String status;

    private UserResponse user;

    private FlightResponse flight;


    public static ReservationResponse toResponse(Reservation reservation) {
        UserResponse user = Objects.isNull(reservation.getUser()) ?
                null : UserResponse.toResponse(reservation.getUser());
        FlightResponse flight = Objects.isNull(reservation.getFlight()) ?
                null : FlightResponse.toResponse(reservation.getFlight());

        return ReservationResponse.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus().name())
                .user(user)
                .flight(flight)
                .build();


    }



}
