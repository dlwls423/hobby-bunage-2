package com.example.hobbybungae2.domain.user.entity;

import com.example.hobbybungae2.domain.comment.entity.Comment;
import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.post.entity.Post;
import com.example.hobbybungae2.domain.user.dto.UserProfileRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User{

	@OneToMany(mappedBy = "user")
	private List<Hobby> hobbyList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> postList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> commentList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String idName;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column
	private String introduction = "";

	@Builder
	public User(Long id, String idName, String name, String introduction, String password) {
		this.id = id;
		this.idName = idName;
		this.name = name;
		this.introduction = introduction;
		this.password = password;
	}

	public void update(UserProfileRequestDto requestDto, String password) {
		if(!requestDto.getName().isEmpty()) this.name = requestDto.getName();
		this.password = password;
		this.introduction = requestDto.getIntroduction();
	}

	public void addHobby(Hobby hobby){
		this.hobbyList.add(hobby);
		if(hobby.getUser() != this){
			hobby.setUser(this);
		}
	}

	public void addPost(Post post){
		this.postList.add(post);
		if(post.getUser() != this){
			post.setUser(this);
		}
	}

	public void addComment(Comment comment){
		this.commentList.add(comment);
		if(comment.getUser() != this){
			comment.setUser(this);
		}
	}
}
