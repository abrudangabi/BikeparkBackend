package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Traseu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraseuRepository extends JpaRepository<Traseu,Long> {
    Optional<List<Traseu>> findAllByBikePark_Id(Long id);
}
