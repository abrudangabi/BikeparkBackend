package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Biker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikerRepository extends JpaRepository<Biker,Long> {
    //Optional<Biker> findByConcurs_Id(Long id);
}
