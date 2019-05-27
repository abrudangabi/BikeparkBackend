package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.BikePark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeParkRepository extends JpaRepository<BikePark,Long> {
    Optional<BikePark> findByConcurs_Id(Long id);
}
