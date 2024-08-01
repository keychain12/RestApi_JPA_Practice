package com.example.jparestapipractice.dto.flight.request;

import com.example.jparestapipractice.domain.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightSaveRequest {
    @NotBlank
    private String flightNumber; // 항공편 번호
    @NotBlank
    private String airline; // 항공사
    @NotNull
    private LocalDateTime departureTime; // 출발 시간
    @NotNull
    private LocalDateTime arrivalTime; // 도착 시간

    @NotNull
    private Long departureAirportId; //출발 공항 Id
    @NotNull
    private Long arrivalAirportId; // 도착 공항 Id

    public Flight toEntity() {
        return Flight.builder()
                .flightNumber(this.flightNumber)
                .airline(this.airline)
                .departureTime(this.departureTime)
                .arrivalTime(this.arrivalTime)
                .build();

    }
}
