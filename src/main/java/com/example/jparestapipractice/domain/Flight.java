package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight { // 항공편
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;
    private String flightNumber; // 항공편 번호
    private String airline; // 항공사
    private LocalDate departureTime; // 출발 시간
    private LocalDate arrivalTime; // 도착 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport; // 출발 공항

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport; // 도착 공항

    @OneToMany(mappedBy = "flight")  // 예약정보
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Seat> seats = new HashSet<>();

    @Builder
    private Flight(String flightNumber, String airline, LocalDate departureTime, LocalDate arrivalTime) {

        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }


    public void addDepartureAirport(Airport departureAirport) { // 편의 메서드 출발 공항 추가
        this.departureAirport = departureAirport;
        departureAirport.getDepartureFlights().add(this);
    }
    public void addArrivalAirport(Airport arrivalAirport) { // 편의 메서드 도착 공항 추가
        this.arrivalAirport = arrivalAirport;
        arrivalAirport.getArrivalFlights().add(this);
    }

    public void update(Flight modified) {
        this.flightNumber = modified.getFlightNumber();
        this.airline = modified.getAirline();
        this.departureTime = modified.getDepartureTime();
        this.arrivalTime = modified.getArrivalTime();
        this.departureAirport = modified.getDepartureAirport();
        this.arrivalAirport = modified.getArrivalAirport();
    }



}
