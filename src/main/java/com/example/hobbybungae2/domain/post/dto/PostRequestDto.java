package com.example.hobbybungae2.domain.post.dto;

import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.state.entity.State;
import java.util.List;
import lombok.Getter;

@Getter
public class PostRequestDto{
	String title;
	String content;
	State state;
	List<Hobby> hobbyList;
}

