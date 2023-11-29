package com.example.hobbybungae2.domain.hobby.entity;

import com.example.hobbybungae2.domain.post.entity.PostHobby;
import com.example.hobbybungae2.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "hobby")
@NoArgsConstructor
public class Hobby {

	@OneToMany(mappedBy = "hobby", cascade = CascadeType.REMOVE)
	//@JsonManagedReference
	private final List<PostHobby> postHobbyList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	//@JsonBackReference
	User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String hobbyName;

	@Builder
	public Hobby(String hobbyName) {
		this.hobbyName = hobbyName;
	}

	public void setUser(User user){
		this.user = user;
		if(!user.getHobbyList().contains(this)){
			user.addHobby(this);
		}
	}

	public void addPostHobby(PostHobby postHobby){
		this.postHobbyList.add(postHobby);
		if(postHobby.getHobby() != this){
			postHobby.setHobby(this);
		}
	}
}
