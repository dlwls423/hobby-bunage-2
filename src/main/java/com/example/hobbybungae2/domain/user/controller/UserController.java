package com.example.hobbybungae2.domain.user.controller;

import com.example.hobbybungae2.domain.user.dto.UserProfileRequestDto;
import com.example.hobbybungae2.domain.user.dto.UserProfileResponseDto;
import com.example.hobbybungae2.domain.user.dto.UserRequestDto;
import com.example.hobbybungae2.domain.user.dto.UserResponseDto;
import com.example.hobbybungae2.domain.user.service.UserService;
import com.example.hobbybungae2.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-bungae/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid UserRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponseDto> getUser (
        @PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponseDto responseDto = userService.getUser(userId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    // 프로필 수정
    @PatchMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponseDto> updateUser(
        @PathVariable Long userId, @RequestBody UserProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponseDto responseDto = userService.updateUser(userId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }
}
