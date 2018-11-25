package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Locatie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocatieRepository extends JpaRepository<Locatie,Long> {
}
