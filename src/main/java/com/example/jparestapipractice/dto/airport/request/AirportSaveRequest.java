package com.example.jparestapipractice.dto.airport.request;

import com.example.jparestapipractice.domain.Airport;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter@Setter
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class AirportSaveRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotBlank
    private String city;
    @NotBlank
    private String country;

    public  Airport toEntity() {
        return Airport.builder()
                .name(this.name)
                .code(this.code)
                .city(this.city)
                .country(this.country)
                .build();
    }


}
