package com.example.jparestapipractice.testdata;

import com.example.jparestapipractice.domain.Seat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeatRequest {

    private String seatNum;
    private String classType;
    private Long price;

    public Seat toEntity() {
        return Seat.builder()
                .seatNumber(this.seatNum)
                .classType(this.classType)
                .price(this.price)
                .build();

    }
}
