package com.example.hobbybungae2.domain.post.entity;

import com.example.hobbybungae2.domain.comment.entity.Comment;
import com.example.hobbybungae2.domain.common.TimeStamp;
import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.post.dto.PostRequestDto;
import com.example.hobbybungae2.domain.state.entity.State;
import com.example.hobbybungae2.domain.user.entity.User;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends TimeStamp {

	@OneToMany(mappedBy = "post")
	private final List<PostHobby> postHobbyList = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) // 댓글은 게시글에 의해 관리됨
	private final List<Comment> commentList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	private String title;

	@Column(nullable = false, length = 500)
	private String content;

	public Post(PostRequestDto requestDto, User user){
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.state = requestDto.getState();
		this.user = user;
	}

	public void setState(State state){
		this.state = state;
	}

	public void setUser(User user){
		this.user = user;
	}

	public void update(PostRequestDto requestDto, User user) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.state = requestDto.getState();
		this.user = user;
	}
}
