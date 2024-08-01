package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airport { // 공항 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "airport_id")
    private Long id;

    private String name; //공항 이름
    private String code; // 공항 코드

    @OneToMany(mappedBy = "departureAirport" , cascade = CascadeType.REMOVE)
    private List<Flight> departureFlights = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalAirport" , cascade = CascadeType.REMOVE)
    private List<Flight> arrivalFlights = new ArrayList<>();

    @Embedded
    private Address address; // 주소 / 국가 , 도시

    @Builder
    public Airport(String name, String code, String city, String country) {
        this.name = name;
        this.code = code;
        this.address = Address.builder().city(city).country(country).build();
    }


}
