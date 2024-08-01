package com.example.jparestapipractice.service;

import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.repository.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService {
    private final AirportRepository airportRepository;

    @Transactional(readOnly = true)
    public List<Airport> findAll() { // 모든 공항 조회
        return airportRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Airport getAirportById(Long airportId) { // 단일 공항 조회
        return airportRepository.findById(airportId).orElseThrow(
                () -> new EntityNotFoundException("해당 공항을 찾을 수 없습니다."));
    }

    public Long addAirport(Airport airport) { // 공항 추가
        return airportRepository.save(airport).getId();
    }
}
