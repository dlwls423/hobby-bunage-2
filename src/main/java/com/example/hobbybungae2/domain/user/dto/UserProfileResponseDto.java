package com.example.hobbybungae2.domain.user.dto;


import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.user.entity.User;
import java.util.List;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

	Long id;

	String idName;

	String name;

	String introduction;

	List<String> hobbyNameList;

	public UserProfileResponseDto(User newUser) {
		this.id = newUser.getId();
		this.idName = newUser.getIdName();
		this.name = newUser.getName();
		this.introduction = newUser.getIntroduction();
		this.hobbyNameList = newUser.getUserHobbyList().stream().map(userHobby -> userHobby.getHobby().getHobbyName()).toList();
	}
}
