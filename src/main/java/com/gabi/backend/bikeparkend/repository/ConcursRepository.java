package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Concurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcursRepository extends JpaRepository<Concurs,Long> {
}
