package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation { //예약 도메인
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reservation_id")
    private Long id;
    private LocalDate reservationDate; // 예약날짜

    @Enumerated(EnumType.STRING)
    private Status status; // 예약상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 예약 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight; // 예약한 항공편

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Builder
    public Reservation(LocalDate reservationDate, Status status) {
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // 연관 관계 메서드
    public void setUser(User user) {
        this.user = user;
        this.user.getReservations().add(this);
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        this.flight.getReservations().add(this);

    }

    public void cancel() { // 예약취소 메서드
        this.status = Status.CANCELLED;
    }

}
