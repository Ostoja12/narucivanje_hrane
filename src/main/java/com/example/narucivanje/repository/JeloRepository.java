package com.example.narucivanje.repository;
import com.example.narucivanje.model.Jelo;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author necao
 */
public interface JeloRepository extends JpaRepository<Jelo, Long>{
    boolean existsByNazivAndRestoranId(String naziv, Long restoranId);
    List<Jelo> findByRestoranId(Long restoranId);
    List<Jelo> findByRestoranIdAndDostupnostTrue(Long restoranId);
    
//    List<Jelo> findByNazivContainingIgnoreCase(String naziv);
//    List<Jelo> findByCenaLessThanEqual(BigDecimal cena);
    
    
    @Query("SELECT j from Jelo where " + 
            "(:restoranId IS NULL or j.restoran.id = :restoranId AND " + 
            "(:kategorijaId IS NULL or j.kategorija.id = :kategorijaId AND " +
            "(:maxCena IS NULL or j.cena <= :maxCena) AND "
            + "(:naziv IS NULL or LOWER(j.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))) AND " +
            "j.dostupnost = true")
    Page<Jelo> pretraziJela(
            @Param("restoranId")Long restoranId,
            @Param("kategorijaId")Long kategorijaId,
            @Param("maxCena")BigDecimal maxCena,
            @Param("naziv")String naziv,
            Pageable pageable
    );
   
}
