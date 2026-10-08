/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.service;

import com.example.narucivanje.dto.KategorijaDto;
import com.example.narucivanje.model.Kategorija;
import com.example.narucivanje.repository.KategorijaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.narucivanje.mapper.KategorijaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author necao
 */
@RequiredArgsConstructor
@Service
public class KategorijaService {
    private final KategorijaRepository repo;
    private final KategorijaMapper kategorijaMapper;
   
  
    @Transactional(readOnly = true)
    public List<KategorijaDto> findAll()
    {
        List<Kategorija> entitetiIzBaze = repo.findAll();
        return entitetiIzBaze.stream().map(kategorijaMapper::toDto).toList();
    }
    @Transactional(readOnly = true)
    public KategorijaDto findById(Long id)
    {
        Kategorija entity =  repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Nema kategorije sa id" + id));
        return kategorijaMapper.toDto(entity);
    }
    @Transactional
    public KategorijaDto kreiraj(KategorijaDto kategorijaDto)
    {
        if(repo.existsByNaziv(kategorijaDto.getNaziv()))
        {
            throw new IllegalArgumentException("kategorija sa nazivom: " + kategorijaDto.getNaziv() + " vec postoji!");
        }
        Kategorija entity = kategorijaMapper.toEntity(kategorijaDto);
        Kategorija sacuvana = repo.save(entity);
        return kategorijaMapper.toDto(sacuvana);
        
    }
    @Transactional
    public KategorijaDto izmeni(Long id, KategorijaDto nova)
    {
        Kategorija stara = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Kategorija sa id: " + id + " ne postoji"));
        if(!stara.getNaziv().equalsIgnoreCase(nova.getNaziv()) && repo.existsByNaziv(nova.getNaziv()))
        {
            throw new IllegalArgumentException("kategorija sa nazivom :" + nova.getNaziv() + " vec postoji");
        }
        stara.setNaziv(nova.getNaziv());
        Kategorija izmenjena = repo.save(stara);
        return kategorijaMapper.toDto(izmenjena);
    }
    @Transactional
    public void obrisi(Long id)
    {
        Kategorija postojeca = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Kategorija sa id: " + id + " ne postoji"));
        repo.delete(postojeca);
    }
    
}
