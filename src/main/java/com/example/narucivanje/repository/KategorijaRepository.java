/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.narucivanje.repository;

import com.example.narucivanje.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author necao
 */
public interface KategorijaRepository extends JpaRepository<Kategorija, Long>{
    boolean existsByNaziv(String naziv);
}
