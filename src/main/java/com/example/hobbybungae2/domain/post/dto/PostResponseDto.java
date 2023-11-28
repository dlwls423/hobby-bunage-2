package com.example.hobbybungae2.domain.post.dto;


import com.example.hobbybungae2.domain.hobby.entity.Hobby;
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

}
