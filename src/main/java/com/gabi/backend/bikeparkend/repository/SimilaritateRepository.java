package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Similaritati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimilaritateRepository extends JpaRepository<Similaritati,Long> {
}
