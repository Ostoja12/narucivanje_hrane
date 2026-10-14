/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.mapper;

import com.example.narucivanje.dto.PorudzbinaDto;
import com.example.narucivanje.model.Porudzbina;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

/**
 *
 * @author necao
 */
@Component
@RequiredArgsConstructor
public class PorudzbinaMapper implements BaseMapper<Porudzbina, PorudzbinaDto>{
    private final StavkaPorudzbineMapper stavkaMapper;
    @Override
    public PorudzbinaDto toDto(Porudzbina entity) {
        if(entity == null) return null;
        PorudzbinaDto pdto = new PorudzbinaDto();
        pdto.setAdresaDostave(entity.getAdresaDostave());
        pdto.setDatumVreme(entity.getDatumVreme());
        pdto.setId(entity.getId());
        if(entity.getRestoran() != null)
        {
            pdto.setIdRestoran(entity.getRestoran().getId());
            pdto.setNazivRestorana(entity.getRestoran().getNaziv());
        }
        if(entity.getKorisnik() != null)
        {
            pdto.setIdKorisnik(entity.getKorisnik().getId());
            pdto.setImeKorisnika(entity.getKorisnik().getIme());
            pdto.setPrezimeKorisnika(entity.getKorisnik().getPrezime());
        }
        pdto.setStatus(entity.getStatus());
        pdto.setUkupnaCena(entity.getUkupnaCena());
        if(entity.getStavke() != null)
        {
            pdto.setStavke(entity.getStavke().stream().map(stavkaMapper :: toDto).toList());
        }
        return pdto;
    }

    @Override
    public Porudzbina toEntity(PorudzbinaDto dto) {
        if(dto == null)
            return null;
        Porudzbina p = new Porudzbina();
        p.setAdresaDostave(dto.getAdresaDostave());
        p.setId(dto.getId());
        p.setStatus(dto.getStatus());
        
        if(dto.getStavke()!= null)
        {
            p.setStavke(dto.getStavke().stream().map(stavkaMapper:: toEntity).toList());
        }
        return p;
    }
    
}
