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

	String stateDo;

	String stateSi;

	String stateGu;

	String userIdName;

	List<String> hobbyNameList;

	public PostResponseDto(Post savePost) {
		this.postId = savePost.getId();
		this.title = savePost.getTitle();
		this.content = savePost.getContent();
		this.createdAt = savePost.getCreatedAt();
		this.modifiedAt = savePost.getModifiedAt();
		this.stateDo = savePost.getState().getStateDo();
		this.stateSi = savePost.getState().getStateSi();
		this.stateGu = savePost.getState().getStateGu();
		this.userIdName = savePost.getUser().getIdName();
		this.hobbyNameList = savePost.getPostHobbyList().stream()
			.map(PostHobby::getHobby).map(Hobby::getHobbyName).toList();
	}
}
