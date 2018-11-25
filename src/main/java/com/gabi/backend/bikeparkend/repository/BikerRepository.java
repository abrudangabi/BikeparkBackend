package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Biker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikerRepository extends JpaRepository<Biker,Long> {
}
