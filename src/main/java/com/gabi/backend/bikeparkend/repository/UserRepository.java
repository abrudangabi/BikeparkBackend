package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
