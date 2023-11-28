package com.example.hobbybungae2.domain.user.dto;


import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Getter;

@Getter
public class UserResponseDto{

	Long id;

	String idName;

	String name;

	String introduction;

	List<Hobby> hobbyList;

}
