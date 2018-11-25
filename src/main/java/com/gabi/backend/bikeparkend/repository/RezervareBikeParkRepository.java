package com.gabi.backend.bikeparkend.repository;


import com.gabi.backend.bikeparkend.model.RezervareBikePark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervareBikeParkRepository extends JpaRepository<RezervareBikePark,Long> {
}
