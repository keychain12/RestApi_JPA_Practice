package com.example.jparestapipractice.testdata;

import com.example.jparestapipractice.domain.Seat;
import lombok.*;

import static com.example.jparestapipractice.domain.QSeat.seat;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SeatResponse {

    private String seatNumber;
    private String classType;
    private Long price;

    public static SeatResponse toResponse(Seat seat) {
        return SeatResponse.builder()
                .seatNumber(seat.getSeatNumber())
                .classType(seat.getClassType())
                .price(seat.getPrice())
                .build();
    }





}
