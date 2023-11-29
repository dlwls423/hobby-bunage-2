package com.example.hobbybungae2.domain.post.service;

import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.hobby.exception.NotFoundHobbyException;
import com.example.hobbybungae2.domain.hobby.service.HobbyService;
import com.example.hobbybungae2.domain.post.dto.PostRequestDto;
import com.example.hobbybungae2.domain.post.dto.PostResponseDto;
import com.example.hobbybungae2.domain.post.entity.Post;
import com.example.hobbybungae2.domain.post.entity.PostHobby;
import com.example.hobbybungae2.domain.post.exception.InvalidPostModifierException;
import com.example.hobbybungae2.domain.post.exception.NotFoundPostException;
import com.example.hobbybungae2.domain.post.repository.PostHobbyRepository;
import com.example.hobbybungae2.domain.post.repository.PostRepository;
import com.example.hobbybungae2.domain.state.entity.State;
import com.example.hobbybungae2.domain.state.exception.NotFoundStateException;
import com.example.hobbybungae2.domain.state.service.StateService;
import com.example.hobbybungae2.domain.user.entity.User;
import com.example.hobbybungae2.domain.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {

	private final PostRepository postRepository;

	private final PostHobbyRepository postHobbyRepository;

	private final HobbyService hobbyService;

	private final StateService stateService;

	private final UserService userService;

	@Transactional(readOnly = true)
	public PostResponseDto getPost(Long postId) {
		Post post = getPostById(postId);
		return new PostResponseDto(post);
	}

	@Transactional(readOnly = true)
	public List<PostResponseDto> getPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc().stream()
			.map(PostResponseDto::new)
			.collect(Collectors.toList());
	}

	public PostResponseDto addPost(PostRequestDto requestDto, User user) {
		// 취미카테고리 & 지역 데이터 존재여부 검증
		hobbyService.validateHobbyExistence(requestDto.getHobbyList().stream().map(Hobby::getHobbyName).toList());

		// Dto -> Entity
		State saveState = stateService.validateStateExistence(requestDto.getState());
		User saveUser = userService.getUserEntity(user.getId());
		Post post = new Post(requestDto, saveState, saveUser);
		saveUser.getPostList().add(post);
		Post savePost = postRepository.save(post);

		for(Hobby hobby : requestDto.getHobbyList()){
			Hobby saveHobby = hobbyService.findHobbyByHobbyName(hobby.getHobbyName());
			PostHobby postHobby = new PostHobby(savePost, saveHobby);
			saveHobby.getPostHobbyList().add(postHobby);
			saveHobby.getPostHobbyList().add(postHobby);
			postHobbyRepository.save(postHobby);
		}
		return new PostResponseDto(savePost);
	}

	public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user)
		throws InvalidPostModifierException {
		Post post = getPostById(postId);
		validateUserIsAuthor(post.getUser().getId(), user.getId());
		post.update(requestDto, user);
		return new PostResponseDto(post);
	}

	public void deletePost(Long postId, User user) {
		Post post = getPostById(postId);
		validateUserIsAuthor(post.getUser().getId(), user.getId());
		postRepository.delete(post);
	}

	@Transactional(readOnly = true)
	public Post getPostById(Long postId) {
		return postRepository.findById(postId)
			.orElseThrow(() -> new NotFoundPostException("postId", postId.toString()));
	}

	static void validateUserIsAuthor(Long postAuthorId, Long loggedInUserId) throws InvalidPostModifierException {
		if (!postAuthorId.equals(loggedInUserId)) {
			throw new InvalidPostModifierException("postAuthor", postAuthorId.toString(),
				"사용자는 이 게시물을 업데이트/삭제할 권한이 없습니다.");
		}
	}
}
