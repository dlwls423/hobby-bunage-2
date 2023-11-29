package com.example.hobbybungae2.domain.user.service;

import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.hobby.service.HobbyService;
import com.example.hobbybungae2.domain.post.dto.PostRequestDto;
import com.example.hobbybungae2.domain.post.entity.PostHobby;
import com.example.hobbybungae2.domain.user.dto.UserProfileRequestDto;
import com.example.hobbybungae2.domain.user.dto.UserProfileResponseDto;
import com.example.hobbybungae2.domain.user.dto.UserRequestDto;
import com.example.hobbybungae2.domain.user.dto.UserResponseDto;
import com.example.hobbybungae2.domain.user.entity.User;
import com.example.hobbybungae2.domain.user.entity.UserHobby;
import com.example.hobbybungae2.domain.user.exception.DuplicatedUserException;
import com.example.hobbybungae2.domain.user.exception.NotFoundUserException;
import com.example.hobbybungae2.domain.user.repository.UserHobbyRepository;
import com.example.hobbybungae2.domain.user.repository.UserRepository;
import com.example.hobbybungae2.domain.user.exception.NotMatchingUserException;
import com.example.hobbybungae2.domain.user.helper.PasswordCreator;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private static final String DUPLICATED_USER_ERROR_MESSAGE = "중복되지 않는 아이디를 확인해주시길 바랍니다.";

	private final UserRepository userRepository;

	private final UserHobbyRepository userHobbyRepository;

	private final HobbyService hobbyService;

	private final PasswordEncoder passwordEncoder;

	// 회원가입 - 사용자 저장
	public ResponseEntity<UserResponseDto> signUp(UserRequestDto requestDto) {
		verifyDuplicatedUser(requestDto);

		User newUser = User.builder()
			.idName(requestDto.getIdName())
			.name(requestDto.getName())
			.password(passwordEncoder.encode(requestDto.getPassword()))
			.build();
		userRepository.save(newUser);

		return new ResponseEntity<>(new UserResponseDto(newUser), HttpStatus.OK);
	}

	// 사용자 프로필 조회
	public UserProfileResponseDto getUser(Long id, User inputUser) {
		User user = getUserEntity(id);
		validateId(id, inputUser.getId());
		return new UserProfileResponseDto(user);
	}

	// 사용자 프로필 수정
	@Transactional
	public UserProfileResponseDto updateUser(Long id, UserProfileRequestDto requestDto, User signInUser) {
		User saveUser = getUserEntity(id);
		validateId(id, signInUser.getId());
		Optional<String> encodePasswordOrNull = PasswordCreator.createEncodePasswordOrNull(signInUser, requestDto,
			passwordEncoder);

		hobbyService.validateHobbyExistence(requestDto.getHobbyList().stream().map(Hobby::getHobbyName).toList());

		saveUser.update(requestDto, encodePasswordOrNull.orElse(saveUser.getPassword()));
		if(!saveUser.getUserHobbyList().isEmpty()) saveUser.getUserHobbyList().clear();

		saveUserHobby(saveUser, requestDto);

		return new UserProfileResponseDto(saveUser);
	}

	private void verifyDuplicatedUser(UserRequestDto requestDto) {
		if (hasDuplicatedUser(requestDto.getIdName())) {
			throw new DuplicatedUserException("id_name", requestDto.getIdName(), DUPLICATED_USER_ERROR_MESSAGE);
		}
	}

	public boolean hasDuplicatedUser(String idName) {
		return userRepository.findByIdName(idName).isPresent();
	}

	private static void validateId(Long inputId, Long signedInUserId) {
		if (!Objects.equals(inputId, signedInUserId)) {
			throw new NotMatchingUserException("user.id", signedInUserId.toString());
		}
	}

	@Transactional(readOnly = true)
	public User getUserEntity(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException("user_id", userId.toString()));
	}

	private void saveUserHobby(User user, UserProfileRequestDto requestDto){
		for(Hobby hobby : requestDto.getHobbyList()){
			Hobby saveHobby = hobbyService.findHobbyByHobbyName(hobby.getHobbyName());
			UserHobby userHobby = new UserHobby(user, saveHobby);
			userHobbyRepository.save(userHobby);
		}
	}
}
