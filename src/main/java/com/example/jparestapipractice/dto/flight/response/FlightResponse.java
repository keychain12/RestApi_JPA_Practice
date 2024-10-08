package com.example.jparestapipractice.dto.flight.response;

import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.domain.Seat;
import com.example.jparestapipractice.testdata.SeatResponse;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightResponse {

    private String flightNumber;
    private String airline;
    private LocalDate departureTime; // 출발시간
    private LocalDate arrivalTime;  // 도착시간

    private String departureAirportName; //출발 공항 이름

    private String arrivalAirportName;  // 도착 공항 이름



    public static FlightResponse toResponse(Flight flight) {
        return FlightResponse.builder()
                .flightNumber(flight.getFlightNumber())
                .airline(flight.getAirline())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .departureAirportName(flight.getDepartureAirport().getName())
                .arrivalAirportName(flight.getArrivalAirport().getName())
                .build();

    }




}
