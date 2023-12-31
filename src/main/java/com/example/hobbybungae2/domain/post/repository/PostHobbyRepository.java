package com.example.hobbybungae2.domain.post.repository;

import com.example.hobbybungae2.domain.post.entity.PostHobby;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHobbyRepository extends JpaRepository<PostHobby, Long> {

    List<PostHobby> findAllByHobbyId(Long hobbyId);
}
