/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.service;

import com.example.narucivanje.model.Kategorija;
import com.example.narucivanje.repository.KategorijaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author necao
 */
@Service
public class KategorijaService {
    private final KategorijaRepository repo;

    public KategorijaService(KategorijaRepository repo) {
        this.repo = repo;
    }
    
    @Transactional(readOnly = true)
    public List<Kategorija> findAll()
    {
        return repo.findAll();
    }
    @Transactional
    public Kategorija findById(Long id)
    {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Nema kategorije sa id" + id));
    }
    @Transactional
    public Kategorija kreiraj(Kategorija kategorija)
    {
        if(repo.existsByNaziv(kategorija.getNaziv()))
        {
            throw new IllegalArgumentException("kategorija sa nazivom: " + kategorija.getNaziv() + " vec postoji!");
        }
        return repo.save(kategorija);
    }
    @Transactional
    public Kategorija izmeni(Long id, Kategorija nova)
    {
        Kategorija stara = findById(id);
        if(!stara.getNaziv().equals(nova.getNaziv()) && repo.existsByNaziv(nova.getNaziv()))
        {
            throw new IllegalArgumentException("kategorija sa nazivom :" + nova.getNaziv() + " vec postoji");
        }
        stara.setNaziv(nova.getNaziv());
        return repo.save(stara);
    }
    @Transactional
    public void obrisi(Long id)
    {
        Kategorija postojeca = findById(id);
        repo.delete(postojeca);
    }
    
}
