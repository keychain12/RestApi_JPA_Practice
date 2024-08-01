package com.example.jparestapipractice.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address { // 주소 임베디드
    private String city; // 도시
    private String country; // 국가


    @Builder
    private Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public void update(String city, String country) {
        this.city = city;
        this.country = country;
    }
}
