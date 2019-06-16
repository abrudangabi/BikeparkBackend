package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Biker;
import com.gabi.backend.bikeparkend.model.Categorie;
import com.gabi.backend.bikeparkend.model.Preferinte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreferintaRepository extends JpaRepository<Preferinte,Long> {
    //Optional<BikePark> findByConcurs_Id(Long id);
    //Optional<List<Categorie>> findAllByConcurs_Id(Long id);
    //Optional<Preferinte> findByUser_idAndItem_id(Long id1, Long id2);
    List<Preferinte> findByIdEquals(Long id);
    //List<Preferinte> findByUser_id(Biker biker);
    List<Preferinte> findByPreference(Double pref);
}
