package com.example.jparestapipractice.controller;

import com.example.jparestapipractice.common.Result;
import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.dto.airport.reponse.AirportResponse;
import com.example.jparestapipractice.dto.airport.request.AirportSaveRequest;
import com.example.jparestapipractice.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {
    private final AirportService airportService;

    @GetMapping()
    public Page<AirportResponse> getAllAirport(Pageable pageable) { // 모든 공항 목록 조회

        Page<Airport> airport = airportService.findAll(pageable);
        return airport.map(AirportResponse::toResponse);
    }

    @GetMapping("/{airportId}")
    public AirportResponse getAirportById(@PathVariable Long airportId) { // 단일 공항 조회
        Airport airport = airportService.getAirportById(airportId);

        return AirportResponse.toResponse(airport);
    }

    @PostMapping() // 공항 추가
    public Result createAirport(@Valid @RequestBody AirportSaveRequest request) {
        Airport airport = request.toEntity();
        Long id = airportService.addAirport(airport);
        return new Result(id);
    }
}
