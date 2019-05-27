package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Optional<List<Categorie>> findCategorieByConcurs(Long id);
    Optional<List<Categorie>> findAllByConcurs_Id(Long id);
}
