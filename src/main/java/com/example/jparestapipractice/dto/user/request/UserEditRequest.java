package com.example.jparestapipractice.dto.user.request;

import com.example.jparestapipractice.domain.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEditRequest {
    private String username;
    private String password;
    private String email;
    private String tel;
    private String city;
    private String country;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .tel(this.tel)
                .city(this.city)
                .country(this.country)
                .build();
    }
}
