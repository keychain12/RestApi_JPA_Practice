package com.example.jparestapipractice.service;

import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.dto.flight.request.FlightEditRequest;
import com.example.jparestapipractice.dto.flight.request.FlightSaveRequest;
import com.example.jparestapipractice.repository.AirportRepository;
import com.example.jparestapipractice.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Transactional(readOnly = true)
    public List<Flight> getAllFlights() { // 모든 항공편 조회
        return flightRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Flight findById(Long flightId) { // 항공편 단일 조회
        return flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException("없음"));
    }

    public Flight createFlight(FlightSaveRequest request) { // 항공편 추가
        // 출발 , 도착 공항 엔티티 조회
        Airport departureAirport = airportRepository.findById(request.getDepartureAirportId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 공항입니다."));
        Airport arrivalAirport = airportRepository.findById(request.getArrivalAirportId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 공항입니다."));
        // flight 엔티티 생성
        Flight flight = request.toEntity();
        flight.addDepartureAirport(departureAirport);
        flight.addArrivalAirport(arrivalAirport);
       return flightRepository.save(flight);
    }

    public Flight modify(Long flightId, FlightEditRequest request) {
        Flight original = flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException("없음"));
        Long departureAirportId = request.getDepartureAirportId();
        Long arrivalAirportId = request.getArrivalAirportId();
        Airport departure = airportRepository.findById(departureAirportId)
                .orElseThrow(() -> new EntityNotFoundException("없음"));
        Airport arrival = airportRepository.findById(arrivalAirportId).
                orElseThrow(() -> new EntityNotFoundException("없음"));
        Flight modified = request.toEntity();

        modified.addDepartureAirport(departure);
        modified.addArrivalAirport(arrival);

        original.update(modified);
        return original;
    }

    public void remove(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException("없"));
        flightRepository.delete(flight);
    }
}
