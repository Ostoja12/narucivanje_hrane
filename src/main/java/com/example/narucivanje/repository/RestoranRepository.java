package com.example.narucivanje.repository;
import com.example.narucivanje.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author necao
 */
public interface RestoranRepository extends JpaRepository<Restoran, Long>{
    boolean existsByNaziv(String naziv);
    boolean existsById(Long restoranId);
}
