package com.example.jparestapipractice.dto.user.response;

import com.example.jparestapipractice.domain.User;
import com.example.jparestapipractice.repository.UserRepository;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String tel;
    private String city;
    private String country;

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .tel(user.getTel())
                .city(user.getAddress().getCity())
                .country(user.getAddress().getCountry())
                .build();

    }

}
