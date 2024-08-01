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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController { // 항공편 컨트롤라

    private final FlightService flightService;

    @GetMapping()  // 모든 항공편 조회
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}") // 단일 항공편 조회
    public FlightResponse getFlightById(@PathVariable Long flightId) {
        Flight flight = flightService.findById(flightId);
        return FlightResponse.toResponse(flight);
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
