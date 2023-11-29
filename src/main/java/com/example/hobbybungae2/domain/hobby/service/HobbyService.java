package com.example.hobbybungae2.domain.hobby.service;

import com.example.hobbybungae2.domain.hobby.dto.HobbyRequestDto;
import com.example.hobbybungae2.domain.hobby.dto.HobbyResponseDto;
import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.hobby.exception.DuplicatedHobbyException;
import com.example.hobbybungae2.domain.hobby.exception.NotFoundHobbyException;
import com.example.hobbybungae2.domain.hobby.repository.HobbyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HobbyService {

	private final HobbyRepository hobbyRepository;

	public HobbyResponseDto postHobby(HobbyRequestDto requestDto) {
		String hobbyName = requestDto.getHobbyName();

		//중복 검사
		validateDuplication(hobbyName);

		Hobby hobby = Hobby.builder().hobbyName(hobbyName).build();
		hobbyRepository.save(hobby);
		return new HobbyResponseDto(hobby);
	}

	@Transactional(readOnly = true)
	public List<HobbyResponseDto> getHobbies() {
		return hobbyRepository.findAll().stream()
			.map(HobbyResponseDto::new).collect(Collectors.toList());
	}

	public void deleteHobby(Long hobbyId) {
		Hobby hobby = findHobbyById(hobbyId);
		hobbyRepository.delete(hobby);
	}

	private void validateDuplication(String hobbyName) {
		if (hobbyRepository.findByHobbyName(hobbyName).isPresent()) {
			throw new DuplicatedHobbyException("hobby's name", hobbyName);
		}
	}

	private Hobby findHobbyById(Long hobbyId) {
		return hobbyRepository.findById(hobbyId).orElseThrow(
			() -> new NotFoundHobbyException("hobbyId", hobbyId.toString(), "선택한 취미 카테고리 ID가 없습니다")
		);
	}

	@Transactional
	public Hobby findHobbyByHobbyName(String hobbyName) {
		return hobbyRepository.findByHobbyName(hobbyName).orElseThrow(
			() -> new NotFoundHobbyException("hobby's name",hobbyName, "입력하신 취미 카테고리가 없습니다")
		);
	}

	@Transactional
	public void validateHobbyExistence(List<String> hobbyNames) {
		for(String hobbyName : hobbyNames){
			findHobbyByHobbyName(hobbyName);
		}
	}
}
