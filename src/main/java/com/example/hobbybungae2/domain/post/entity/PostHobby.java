package com.example.hobbybungae2.domain.post.entity;

import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post_hobby")
@NoArgsConstructor
public class PostHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby_id", nullable = false)
    private Hobby hobby;

    public PostHobby(Post post, Hobby hobby){
        setPost(post);
        setHobby(hobby);
    }

    public void setPost(Post post){
        this.post = post;
        if(!post.getPostHobbyList().contains(this)){
            post.addPostHobby(this);
        }
    }

    public void setHobby(Hobby hobby){
        this.hobby = hobby;
        if(!hobby.getPostHobbyList().contains(this)){
            hobby.addPostHobby(this);
        }
    }

}
