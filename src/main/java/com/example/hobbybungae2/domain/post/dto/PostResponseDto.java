package com.example.hobbybungae2.domain.post.dto;


import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.post.entity.Post;
import com.example.hobbybungae2.domain.post.entity.PostHobby;
import com.example.hobbybungae2.domain.state.entity.State;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class PostResponseDto {

	Long postId;

	String title;

	String content;

	LocalDateTime createdAt;

	LocalDateTime modifiedAt;

	State state;

	String userIdName;

	List<Hobby> hobbyList;

	public PostResponseDto(Post savePost) {
		this.postId = savePost.getId();
		this.title = savePost.getTitle();
		this.content = savePost.getContent();
		this.createdAt = savePost.getCreatedAt();
		this.modifiedAt = savePost.getModifiedAt();
		this.state = savePost.getState();
		this.userIdName = savePost.getUser().getIdName();
		this.hobbyList = savePost.getPostHobbyList().stream()
			.map(PostHobby::getHobby).toList();
	}
}
