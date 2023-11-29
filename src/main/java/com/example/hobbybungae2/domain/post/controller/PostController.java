package com.example.hobbybungae2.domain.post.controller;

import com.example.hobbybungae2.domain.post.dto.PostRequestDto;
import com.example.hobbybungae2.domain.post.dto.PostResponseDto;
import com.example.hobbybungae2.domain.post.service.PostService;
import com.example.hobbybungae2.security.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hobby-bungae/v1/posts")
@EnableWebMvc
public class PostController {

	private final PostService postService;

	@GetMapping("/{postId}")
	public ResponseEntity<PostResponseDto> getPost(
		@PathVariable Long postId
	) {
		PostResponseDto responseDto = postService.getPost(postId);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	public ResponseEntity<List<PostResponseDto>> getPosts() {
		List<PostResponseDto> responseDto = postService.getPosts();
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping
//	public String addPost(@RequestBody PostRequestDto requestDto,
//	public PostResponseDto addPost(@RequestBody PostRequestDto requestDto,
	public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) throws JsonProcessingException {
		log.info("Post Controller :: addPost");
		PostResponseDto responseDto = postService.addPost(requestDto, userDetails.getUser());
		log.info("Post Controller **COMPLETED** :: addPost");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
//		return objectMapper.writeValueAsString(requestDto);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostResponseDto> updatePost(
		@PathVariable Long postId,
		@Valid @RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		PostResponseDto responseDto = postService.updatePost(postId, requestDto, userDetails.getUser());
		return ResponseEntity.ok(responseDto);
	}


	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(
		@PathVariable Long postId,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		postService.deletePost(postId, userDetails.getUser());
		return ResponseEntity.noContent().build();
	}
}
