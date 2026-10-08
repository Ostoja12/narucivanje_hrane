/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.service;

import com.example.narucivanje.dto.KorisnikDto;
import com.example.narucivanje.mapper.KorisnikMapper;
import com.example.narucivanje.model.Korisnik;
import com.example.narucivanje.repository.KorisnikRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author necao
 */
@Service
@RequiredArgsConstructor
public class KorisnikService {
    private final KorisnikRepository repo;
    private final KorisnikMapper mapper;
    private final PasswordEncoder passwordEncoder;
    
    public KorisnikDto registracija(KorisnikDto dto)
    {
        if(repo.existsByEmail(dto.getEmail()))
        {
            throw new IllegalArgumentException("korisnik sa datim emailom vec postoji!");
        }
        Korisnik korisnik = mapper.toEntity(dto);
        korisnik.setLozinka(passwordEncoder.encode(dto.getLozinka()));
        
        Korisnik sacuvan = repo.save(korisnik);
        return mapper.toDto(sacuvan);
    }
    public KorisnikDto login(String email, String lozinka)
    {
        Korisnik korisnik = repo.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Korisnik nije pronadjen!"));
        if (!passwordEncoder.matches(lozinka, korisnik.getLozinka())) {
            throw new IllegalArgumentException("Neispravna lozinka!");
        }
        return mapper.toDto(korisnik);
    }
    public List<KorisnikDto> findAll()
    {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }
    public KorisnikDto findById(Long id)
    {
        Korisnik k = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Korisnik nije pronadjen!"));
        return mapper.toDto(k);
    }
    
}
