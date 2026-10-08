package com.example.narucivanje.service;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.example.narucivanje.dto.JeloDto;
import com.example.narucivanje.model.Jelo;
import com.example.narucivanje.model.Kategorija;
import com.example.narucivanje.model.Restoran;
import com.example.narucivanje.repository.JeloRepository;
import com.example.narucivanje.repository.KategorijaRepository;
import com.example.narucivanje.repository.RestoranRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jdk.javadoc.doclet.Reporter;
import com.example.narucivanje.mapper.JeloMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author necao
 */
@Service
public class JeloService {
    private final JeloRepository repo;
    private final RestoranRepository repoRestoran;
    private final KategorijaRepository repoKategorija;
    private final JeloMapper jeloMapper;
    
    public JeloService(JeloRepository repo, RestoranRepository repoRestoran, KategorijaRepository repoKategorija, JeloMapper jeloMapper) {
        this.repo = repo;
        this.repoRestoran = repoRestoran;
        this.repoKategorija = repoKategorija;
        this.jeloMapper = jeloMapper;
    }

    @Transactional
    public JeloDto kreirajJelo(JeloDto dto)
    {
        Restoran restoran = repoRestoran.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Restoran sa Id: " + dto.getRestoranId() + " nije pronadjen!"));
        Kategorija kategorija = repoKategorija.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Kategorija sa id + " + dto.getKategorijaId() + "nije pronadjena"));
        if(repo.existsByNazivAndRestoranId(dto.getNaziv(), dto.getId()))
        {
            throw new IllegalArgumentException("Jelo sa nazivom '" + dto.getNaziv()+ "' vec postoji u ovom restoranu");
        }
        Jelo jelo = jeloMapper.toEntity(dto);
        jelo.setKategorija(kategorija);
        jelo.setRestoran(restoran);
        Jelo sacuvanoJelo = repo.save(jelo);
        return jeloMapper.toDto(sacuvanoJelo);
    }
    @Transactional
    public void obrisiJelo(Long jeloId)
    {
        if(!repo.existsById(jeloId))
        {
            throw new IllegalArgumentException("jelo sa id: '" + jeloId + "' ne postoji");
        }
        repo.deleteById(jeloId);
    }
    @Transactional
    public JeloDto izmeniJelo(Long jeloId, JeloDto dto)
    {
        Jelo postojeceJelo = repo.findById(jeloId).orElseThrow(() -> new EntityNotFoundException("Jelo ne postoji!"));
        Restoran restoran = repoRestoran.findById(dto.getRestoranId()).orElseThrow(() -> new EntityNotFoundException("Restoran ne postoji!"));
        Kategorija kategorija = repoKategorija.findById(dto.getKategorijaId()).orElseThrow(() -> new EntityNotFoundException("kategorija ne postoji!"));
        
        postojeceJelo.setNaziv(dto.getNaziv());
        postojeceJelo.setCena(dto.getCena());
        postojeceJelo.setDostupnost(dto.isDostupnost());
        postojeceJelo.setOpis(dto.getOpis());
        postojeceJelo.setRestoran(restoran);
        postojeceJelo.setKategorija(kategorija);
        
        Jelo sacuvanoJelo = repo.save(postojeceJelo);
        return jeloMapper.toDto(sacuvanoJelo);
    }
    
    public List<JeloDto> findByRestoranId(Long restoranId)
    {
        if(!repoRestoran.existsById(restoranId))
        {
            throw new EntityNotFoundException("Restoran sa id-em: " + restoranId + " ne postoji");
        }
        
        List<Jelo> jela = repo.findByRestoranId(restoranId);
        List<JeloDto> jelaDto = new ArrayList<>();
//        for(Jelo j: jela)
//        {
//            jelaDto.add(jeloMapper.toDto(j));
//        }
//        return jelaDto;
        return repo.findByRestoranId(restoranId).stream().map(jeloMapper::toDto).toList();
    }
    public Page<JeloDto> pretraziJela(Long restoranId,
        Long kategorijaId,
        BigDecimal maxCena,
        String naziv,
        Pageable pageable)
    {
       return repo.pretraziJela(restoranId, kategorijaId, maxCena, naziv, pageable).map(jeloMapper::toDto);
    }
    public JeloDto getById(Long idJelo)
    {
        Jelo jelo = repo.findById(idJelo).orElseThrow(() -> new EntityNotFoundException("Jelo sa id-em: " + idJelo + " ne postoji"));
        return jeloMapper.toDto(jelo);
    }
}
