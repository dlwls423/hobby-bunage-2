package com.example.hobbybungae2.domain.userProfile.controller;

import com.example.hobbybungae.domain.userProfile.dto.UserProfileResponseDto;
import com.example.hobbybungae.domain.userProfile.dto.UserProfileUpdateRequestDto;
import com.example.hobbybungae.domain.userProfile.service.UserProfileService;
import com.example.hobbybungae.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-bungae/v1/users/profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    // 프로필 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> getUser (
            @PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponseDto responseDto = userProfileService.getUser(userId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    // 프로필 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> updateUser(
            @PathVariable Long userId, @RequestBody UserProfileUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponseDto responseDto = userProfileService.updateUser(userId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }
}