/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.service;

import com.example.narucivanje.dto.RestoranDto;
import com.example.narucivanje.mapper.RestoranMapper;
import com.example.narucivanje.model.Restoran;
import com.example.narucivanje.repository.RestoranRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author necao
 */
@Service
@RequiredArgsConstructor

public class RestoranService {
    private final RestoranRepository repo;
    private final RestoranMapper mapper;
    @Transactional(readOnly = true)
    public List<RestoranDto> getAll()
    {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }
    @Transactional(readOnly = true)
    public RestoranDto searchById(Long id)
    {
        Restoran entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("restoran sa id: " + id + " ne postoji!"));
        
        return mapper.toDto(entity);
    }
    @Transactional(readOnly = true)
    public List<RestoranDto> searchByName(String naziv)
    {
        return repo.findByNazivContainingIgnoreCase(naziv).stream().map(mapper::toDto).toList();
    }
    @Transactional
    public RestoranDto addRestoran(RestoranDto dto)
    {
        if(repo.existsByNaziv(dto.getNaziv()))
        {
            throw new IllegalArgumentException("objekat sa nazivom: " + dto.getNaziv() + " vec postoji");
        }
        Restoran sacuvanRestoran = repo.save(mapper.toEntity(dto));
        return mapper.toDto(sacuvanRestoran);
    }
    @Transactional
    public void removeRestoran(Long id)
    {
        Restoran entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("restoran sa id: " + id + " ne postoji!"));
        repo.delete(entity);
    }
    @Transactional
    public RestoranDto izmeniRestoran(Long id, RestoranDto dto)
    {
        Restoran stari = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("restoran sa id: " + id + " ne postoji!"));
        if(repo.existsByNaziv(dto.getNaziv()))
        {
            throw new IllegalArgumentException("objekat sa nazivom " + dto.getNaziv() + " vec postoji");
        }
        stari.setAdresa(dto.getAdresa());
        
        stari.setNaziv(dto.getNaziv());
        stari.setRadnoVreme(dto.getRadnoVreme());
        stari.setTelefon(dto.getTelefon());
        
        return mapper.toDto(stari);
    }
    
    
}
