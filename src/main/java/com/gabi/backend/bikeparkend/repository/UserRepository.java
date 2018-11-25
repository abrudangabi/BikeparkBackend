package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
