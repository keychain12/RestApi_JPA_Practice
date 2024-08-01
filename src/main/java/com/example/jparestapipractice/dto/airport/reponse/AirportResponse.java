package com.example.jparestapipractice.dto.airport.reponse;

import com.example.jparestapipractice.domain.Airport;
import lombok.*;

@Builder
@Getter@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AirportResponse {
    private String name;
    private String code;
    private String city;
    private String country;


    public static AirportResponse toResponse(Airport airport) {
        return AirportResponse.builder()
                .name(airport.getName())
                .code(airport.getCode())
                .city(airport.getAddress().getCity())
                .country(airport.getAddress().getCountry())
                .build();
    }
}
