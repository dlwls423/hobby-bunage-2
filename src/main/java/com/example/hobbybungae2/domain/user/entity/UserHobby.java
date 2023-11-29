package com.example.hobbybungae2.domain.user.entity;

import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import com.example.hobbybungae2.domain.post.entity.Post;
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
@Table(name = "user_hobby")
@NoArgsConstructor
public class UserHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby_id", nullable = false)
    private Hobby hobby;

    public UserHobby(User user, Hobby hobby){
        setUser(user);
        setHobby(hobby);
    }

    public void setUser(User user){
        this.user = user;
        if(!user.getUserHobbyList().contains(this)){
            user.addUserHobby(this);
        }
    }

    public void setHobby(Hobby hobby){
        this.hobby = hobby;
        if(!hobby.getUserHobbyList().contains(this)){
            hobby.addUserHobby(this);
        }
    }

}
