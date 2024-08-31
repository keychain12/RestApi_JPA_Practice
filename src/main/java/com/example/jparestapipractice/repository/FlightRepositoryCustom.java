package com.example.jparestapipractice.repository;

import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.domain.QFlight;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepositoryCustom {
    List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDate departureDate, LocalDate arrivalDate,String travelClass);
}
