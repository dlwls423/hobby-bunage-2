package com.example.hobbybungae2.domain.user.entity;

import com.example.hobbybungae2.domain.common.TimeStamp;
import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User extends TimeStamp {

	@OneToMany(mappedBy = "user")
	private final List<Hobby> hobbyList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String idName;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column
	private String introduction;


	@Builder
	public User(Long id, String idName, String name, String introduction, String password) {
		this.userId = id;
		this.idName = idName;
		this.name = name;
		this.introduction = introduction;
		this.password = password;
	}

}
