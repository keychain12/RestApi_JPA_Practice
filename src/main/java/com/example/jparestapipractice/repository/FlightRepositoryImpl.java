package com.example.jparestapipractice.repository;

import com.example.jparestapipractice.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepositoryCustom {

    private  final JPAQueryFactory queryFactory;

    @Override
    public List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDate departureDate, LocalDate arrivalDate,String travelClass) {
        QFlight flight = QFlight.flight;
        QAirport departureAirportAlias = new QAirport("departureAirport");
        QAirport arrivalAirportAlias = new QAirport("arrivalAirport");
        QSeat seat = QSeat.seat;

        return queryFactory.selectFrom(flight)
                .join(flight.seats, seat)
                .join(flight.departureAirport, departureAirportAlias)
                .join(flight.arrivalAirport, arrivalAirportAlias)
                .where(departureAirportAlias.code.eq(departureAirport)
                        .and(arrivalAirportAlias.code.eq(arrivalAirport))
                        .and(flight.departureTime.eq(departureDate))
                        .and(flight.arrivalTime.eq(arrivalDate))
                        .and(seat.classType.eq(travelClass)))
                .fetch();
    }

}
