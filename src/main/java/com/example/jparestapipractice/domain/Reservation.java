package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reservation { //예약 도메인
    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;
    private LocalDateTime reservationDate; // 예약날짜

    @Enumerated(EnumType.STRING)
    private Status status; // 예약상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 예약 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight; // 예약한 항공편
}
