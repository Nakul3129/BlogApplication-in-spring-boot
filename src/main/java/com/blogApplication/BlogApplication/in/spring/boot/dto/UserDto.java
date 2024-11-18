package com.blogApplication.BlogApplication.in.spring.boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Username should be min of 4 characters")
    private String name;

    @NotEmpty
    @Size(min = 4, max = 8, message = "password should be in range of 4 to 8")
    private String password;

    @NotEmpty
    private String about;

    @Email(message = "Your email address should be valid")
    private String email;
}
