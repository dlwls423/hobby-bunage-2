package com.example.hobbybungae2.domain.post.entity;

import com.example.hobbybungae2.domain.comment.entity.Comment;
import com.example.hobbybungae2.domain.common.TimeStamp;
import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.state.entity.State;
import com.example.hobbybungae2.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private final List<Hobby> hobbyList = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private final List<Comment> commentList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "state_id")
	State state;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@Column(nullable = false, length = 20)
	private String title;

	@Column(nullable = false, length = 500)
	private String content;


}
