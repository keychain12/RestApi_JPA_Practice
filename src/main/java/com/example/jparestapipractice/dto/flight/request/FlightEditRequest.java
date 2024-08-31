package com.example.jparestapipractice.dto.flight.request;

import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.domain.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightEditRequest {

    private String flightNumber; // 항공편 번호
    private String airline; // 항공사
    private LocalDate departureTime; // 출발 시간
    private LocalDate arrivalTime; // 도착 시간
    private Long departureAirportId; // 출발 공항
    private Long arrivalAirportId; // 도착 공항

    public Flight toEntity() {
        return Flight.builder()
                .flightNumber(this.flightNumber)
                .airline(this.airline)
                .departureTime(this.departureTime)
                .arrivalTime(this.arrivalTime)
                .build();
    }
}
