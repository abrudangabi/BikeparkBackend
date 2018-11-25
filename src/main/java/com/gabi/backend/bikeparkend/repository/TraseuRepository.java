package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Traseu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraseuRepository extends JpaRepository<Traseu,Long> {
}
