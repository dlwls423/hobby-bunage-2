package com.example.hobbybungae2.domain.user.repository;


import com.example.hobbybungae2.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdName(String idName);
}
