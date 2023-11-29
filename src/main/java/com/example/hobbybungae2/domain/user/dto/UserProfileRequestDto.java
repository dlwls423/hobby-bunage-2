package com.example.hobbybungae2.domain.user.dto;

import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public class UserProfileRequestDto {
    @Nullable
    @Pattern(regexp = "^[가-힣a-zA-Z]*$")
    String name;

    @Nullable
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\S]*$") // 특수문자 O
    @Size(min = 6, max = 20)
    String password;

    @Nullable
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\S]*$") // 특수문자 O
    @Size(min = 6, max = 20)
    String newPassword;

    String introduction;

    List<Hobby> hobbyList;
}
