package com.example.jparestapipractice.dto.user.request;

import com.example.jparestapipractice.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSaveRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$")
    private String tel;
    @NotBlank
    private String city;
    @NotBlank
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
