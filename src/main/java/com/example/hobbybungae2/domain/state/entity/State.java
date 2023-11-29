package com.example.hobbybungae2.domain.state.entity;

import com.example.hobbybungae2.domain.post.entity.Post;
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
@Table(name = "state")
public class State {

	@OneToMany(mappedBy = "state")
	private List<Post> postList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "do")
	private String stateDo;

	@Column(name = "si")
	private String stateSi;

	@Column(name = "gu")
	private String stateGu;

	@Builder
	public State(String stateDo, String stateSi, String stateGu) {
		this.stateDo = stateDo;
		this.stateSi = stateSi;
		this.stateGu = stateGu;
	}

	public void addPost(Post post){
		this.postList.add(post);
		if(post.getState() != this){
			post.setState(this);
		}
	}
}
