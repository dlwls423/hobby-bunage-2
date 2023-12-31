package com.example.hobbybungae2.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto{
    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$") // 특수문자 X
    @Size(min = 4, max = 10)
    String idName;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z]*$")
    String name;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\S]*$") // 특수문자 O
    @Size(min = 6, max = 20)
    String password;

}
