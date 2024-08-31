package com.example.jparestapipractice.controller;

import com.example.jparestapipractice.common.Result;
import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.dto.flight.request.FlightEditRequest;
import com.example.jparestapipractice.dto.flight.request.FlightSaveRequest;
import com.example.jparestapipractice.dto.flight.response.FlightResponse;
import com.example.jparestapipractice.service.AirportService;
import com.example.jparestapipractice.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController { // 항공편 컨트롤라

    private final FlightService flightService;

    @GetMapping()  // 모든 항공편 조회
    public Page<FlightResponse> getAllFlights(Pageable pageable) {

        Page<Flight> allFlights = flightService.getAllFlights(pageable);

        return allFlights.map(FlightResponse::toResponse);
    }

    @GetMapping("/{flightId}") // 단일 항공편 조회
    public FlightResponse getFlightById(@PathVariable Long flightId) {
        Flight flight = flightService.findById(flightId);
        return FlightResponse.toResponse(flight);
    }
    @GetMapping("/search") // 항공권 검색
    public List<FlightResponse> searchFlights(@RequestParam String departureAirportCode,
                                              @RequestParam String  arrivalAirportCode,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivalDate,
                                              @RequestParam String travelClass){


        List<Flight> flights = flightService.searchFlights(departureAirportCode, arrivalAirportCode, departureDate, arrivalDate,travelClass);
        return flights.stream()
                .map(FlightResponse::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping() // 항공편 추가
    public FlightResponse createFlight(@Valid @RequestBody FlightSaveRequest request) {

        Flight flight = flightService.createFlight(request);

        return FlightResponse.toResponse(flight);
    }

    @PatchMapping("/{flightId}") // 항공편 수정
    public FlightResponse editFlight(@PathVariable Long flightId, @RequestBody FlightEditRequest request) {
        Flight modified = flightService.modify(flightId, request);
        return FlightResponse.toResponse(modified);
    }

    @DeleteMapping("/{flightId}") // 항공편 삭제
    public Result deleteFlight(@PathVariable Long flightId) {
        flightService.remove(flightId);

        return new Result<>(flightId);
    }

}
