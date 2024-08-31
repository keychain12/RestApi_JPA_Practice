package com.example.jparestapipractice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "usertbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User { // 사용자

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;  // 이름
    private String password; // 비번
    private String email; // 이메일
    private String tel; // 전화번호
    @Embedded
    private Address address; // 주소 / 국가 , 도시

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();


    @Builder
    private User(String username, String password, String email, String tel,String city,String country) {
        this.username = username;
        this.password =password;
        this.email = email;
        this.tel = tel;
        this.address = Address.builder().city(city).country(country).build();
    }

    public void update(User modified) {
        this.username = modified.getUsername();
        this.password = modified.getPassword();
        this.email = modified.getEmail();
        this.tel = modified.getTel();
        this.address.update(modified.getAddress().getCity(), modified.getAddress().getCountry());
    }



}
