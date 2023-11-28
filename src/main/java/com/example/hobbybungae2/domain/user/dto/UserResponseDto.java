package com.example.hobbybungae2.domain.user.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record UserResponseDto(
	String idName,
	String name) {

}
