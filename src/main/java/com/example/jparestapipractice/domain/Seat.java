package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
    @Id
    @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    private String seatNumber;

    private String classType;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Builder
    public Seat(String seatNumber, String classType, Long price) {
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.price = price;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        this.flight.seats.add(this);
    }
}
