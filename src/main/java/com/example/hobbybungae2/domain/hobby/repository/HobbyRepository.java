package com.example.hobbybungae2.domain.hobby.repository;


import com.example.hobbybungae2.domain.hobby.entity.Hobby;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

	Optional<Hobby> findByHobbyName(String hobbyName);
}
