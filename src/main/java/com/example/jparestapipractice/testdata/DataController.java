package com.example.jparestapipractice.testdata;

import com.example.jparestapipractice.common.Result;
import com.example.jparestapipractice.domain.Flight;
import com.example.jparestapipractice.domain.Seat;
import com.example.jparestapipractice.dto.flight.response.FlightResponse;
import com.example.jparestapipractice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataRepository repository;
    private final FlightRepository flightRepository;

    @GetMapping("/seat/{flightId}")
    public Result createSeat(@PathVariable Long flightId, @RequestBody SeatRequest request) {
        Seat seat = request.toEntity();
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        seat.setFlight(flight);

        repository.save(seat);

        return new Result(seat.getId());
    }
}
