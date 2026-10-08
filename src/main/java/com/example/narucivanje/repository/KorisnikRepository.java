/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.narucivanje.repository;

import com.example.narucivanje.model.Korisnik;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author necao
 */
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
    Optional<Korisnik> findByEmail(String email);
    boolean existsByEmail(String email);
}
