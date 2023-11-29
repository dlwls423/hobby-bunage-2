package com.example.hobbybungae2.domain.user.helper;

import com.example.hobbybungae2.domain.user.dto.UserProfileRequestDto;
import com.example.hobbybungae2.domain.user.entity.User;
import com.example.hobbybungae2.domain.user.exception.NotMatchingUserException;
import com.example.hobbybungae2.domain.user.exception.NotMatchingPasswordException;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCreator {

	public static Optional<String> createEncodePasswordOrNull(User user, UserProfileRequestDto requestDto,
		PasswordEncoder passwordEncoder) throws NotMatchingUserException {
		String requestPassword = requestDto.getNewPassword();
		if (!requestPassword.isEmpty()) {
			if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
				throw new NotMatchingPasswordException("password", "입력 비밀번호");
			}
			return Optional.of(passwordEncoder.encode(requestPassword));
		}
		return Optional.empty();
	}
}
