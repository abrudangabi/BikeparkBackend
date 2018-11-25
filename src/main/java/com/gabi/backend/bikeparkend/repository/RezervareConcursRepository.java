package com.gabi.backend.bikeparkend.repository;


import com.gabi.backend.bikeparkend.model.RezervareConcurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervareConcursRepository extends JpaRepository<RezervareConcurs,Long> {
}
