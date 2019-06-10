package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Preferinte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferintaRepository extends JpaRepository<Preferinte,Long> {
}
