package com.example.hobbybungae2.domain.hobby.entity;

import com.example.hobbybungae2.domain.post.entity.Post;
import com.example.hobbybungae2.domain.post.entity.PostHobby;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "hobby")
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Hobby {

	@OneToMany(mappedBy = "hobby")
	private final List<PostHobby> postHobbyList = new ArrayList<>();

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String hobbyName;

}
