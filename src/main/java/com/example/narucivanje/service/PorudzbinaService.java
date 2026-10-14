/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.service;

import com.example.narucivanje.dto.PorudzbinaDto;
import com.example.narucivanje.dto.PreporukaOdgovorDto;
import com.example.narucivanje.dto.PreporukaZahtevDto;
import com.example.narucivanje.dto.StavkaPorudzbineDto;
import com.example.narucivanje.mapper.PorudzbinaMapper;
import com.example.narucivanje.mapper.StavkaPorudzbineMapper;
import com.example.narucivanje.model.Korisnik;
import com.example.narucivanje.model.Porudzbina;
import com.example.narucivanje.model.Restoran;
import com.example.narucivanje.model.StavkaPorudzbine;
import com.example.narucivanje.repository.KorisnikRepository;
import com.example.narucivanje.repository.PorudzbinaRepository;
import com.example.narucivanje.repository.RestoranRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class PorudzbinaService {
    private final PorudzbinaRepository porudzbinaRepo;
    private final PorudzbinaMapper mapper;
    private final StavkaPorudzbineMapper snMapper;
    private final KorisnikRepository korisnikRepo;
    private final RestoranRepository restoranRepo;
    private final AiRecommendationService aiRecommendationService;
    @Transactional(readOnly = true)
    public PorudzbinaDto findById(Long id)
    {
        Porudzbina pronadjena = porudzbinaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("porudzbina sa id: " + id + " ne postoji"));
        return mapper.toDto(pronadjena);
    }
    @Transactional(readOnly = true)
    public List<PorudzbinaDto> getAll()
    {
        List<Porudzbina> porudzbine = porudzbinaRepo.findAll();
        return porudzbine.stream().map(mapper::toDto).toList();
    }
    @Transactional
    public PorudzbinaDto save(PorudzbinaDto dto)
    {
        Porudzbina p = mapper.toEntity(dto);
        
        Korisnik k = korisnikRepo.findById(dto.getIdKorisnik()).orElseThrow(() -> new EntityNotFoundException("Korisnik sa id: " + dto.getIdKorisnik() + " nije pronadjen"));
        p.setKorisnik(k);
        Restoran r = restoranRepo.findById(dto.getIdRestoran()).orElseThrow(() -> new EntityNotFoundException("Restoran sa id: " + dto.getIdRestoran() + " nije pronadjen"));
        p.setRestoran(r);
        
        p.setDatumVreme(LocalDateTime.now());
        
        BigDecimal novaCena = BigDecimal.ZERO;
        if(p.getStavke() != null && !p.getStavke().isEmpty())
        {
            for(StavkaPorudzbine sp: p.getStavke())
            {
                BigDecimal cenaStavke = sp.getCena().multiply(BigDecimal.valueOf(sp.getKolicina()));
                novaCena = novaCena.add(cenaStavke);
            }
        }
        p.setUkupnaCena(novaCena);
        
        Porudzbina sacuvana = porudzbinaRepo.save(p);
        return mapper.toDto(sacuvana);
    }
    @Transactional
    public PorudzbinaDto update(PorudzbinaDto dto, Long id) {
        
        Porudzbina postojecaP = porudzbinaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Porudzbina sa id: " + id + " ne postoji"));

        
        Korisnik k = korisnikRepo.findById(dto.getIdKorisnik())
                .orElseThrow(() -> new EntityNotFoundException("Korisnik sa id: " + dto.getIdKorisnik() + " nije pronadjen"));
        Restoran r = restoranRepo.findById(dto.getIdRestoran())
                .orElseThrow(() -> new EntityNotFoundException("Restoran sa id: " + dto.getIdRestoran() + " nije pronadjen"));

        postojecaP.setKorisnik(k);
        postojecaP.setRestoran(r);
        
        
        postojecaP.setAdresaDostave(dto.getAdresaDostave());
        postojecaP.setStatus(dto.getStatus());
        
        
        if (dto.getStavke() != null && !dto.getStavke().isEmpty()) {
            
            List<StavkaPorudzbine> noveStavke = new ArrayList<>();

            BigDecimal novaCena = BigDecimal.ZERO;
            for (StavkaPorudzbineDto spdto : dto.getStavke()) {
                StavkaPorudzbine sp = snMapper.toEntity(spdto);
                sp.setPorudzbina(postojecaP);
                
                if (sp.getCena() != null) {
                    BigDecimal cenaStavke = sp.getCena().multiply(BigDecimal.valueOf(sp.getKolicina()));
                    novaCena = novaCena.add(cenaStavke);
                }
                noveStavke.add(sp);
            }
            postojecaP.setUkupnaCena(novaCena);
            
            postojecaP.getStavke().clear();
            postojecaP.getStavke().addAll(noveStavke);
            
           
        }
        
        PreporukaOdgovorDto aiOdgovor = null;
        Porudzbina izmenjena = porudzbinaRepo.save(postojecaP);
        try{
            List<String> naziviArtikala = new ArrayList<>();
            if(izmenjena.getStavke() != null)
            {
                for(StavkaPorudzbine sp: izmenjena.getStavke())
                {
                    if(sp.getJelo() != null && sp.getJelo().getNaziv()!= null)
                    {
                        naziviArtikala.add(sp.getJelo().getNaziv());
                    }
                }
            }
            Long korisnikId = (izmenjena.getKorisnik()!= null)? izmenjena.getKorisnik().getId(): null;
            
            PreporukaZahtevDto zahtev = new PreporukaZahtevDto(korisnikId, naziviArtikala);
            aiOdgovor = aiRecommendationService.generisiPreporuku(zahtev);
            
            
        }
        catch(Exception e)
        {
            System.err.println("AI preporuka nije uspela: " + e.getMessage());
        }
        PorudzbinaDto dtoOdgovor = mapper.toDto(izmenjena);
        dtoOdgovor.setAiPreporuka(aiOdgovor.getRazlogPreporuke()); // prilagodi prema tvom DTO-u za AI
        
        return dtoOdgovor;
    }
    @Transactional
    public void delete(Long id)
    {
        Porudzbina p = porudzbinaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Porudzbina sa id: " + id + " ne postoji!"));
        porudzbinaRepo.delete(p);
    }
}
