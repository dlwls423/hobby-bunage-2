package com.example.hobbybungae2.domain.user.dto;

import com.example.hobbybungae2.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto{

	Long id;

	String idName;

	String name;

	public UserResponseDto(User newUser) {
		this.id = newUser.getId();
		this.idName = newUser.getIdName();
		this.name = newUser.getName();
	}
}
