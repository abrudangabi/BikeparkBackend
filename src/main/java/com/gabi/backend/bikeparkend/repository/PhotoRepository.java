package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
