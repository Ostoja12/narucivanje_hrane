/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.mapper;

import com.example.narucivanje.dto.KorisnikDto;
import com.example.narucivanje.model.Korisnik;

/**
 *
 * @author necao
 */
public class KorisnikMapper implements BaseMapper<Korisnik, KorisnikDto>{
    @Override
    public KorisnikDto toDto(Korisnik entity) {
        if(entity == null)
            return null;
        KorisnikDto dto = new KorisnikDto();
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setLozinka(entity.getLozinka());
        dto.setPrezime(entity.getPrezime());
        dto.setTelefon(entity.getTelefon());
        dto.setUloga(entity.getUloga());
        return dto;
    }

    @Override
    public Korisnik toEntity(KorisnikDto dto) {
        if(dto == null)
            return null;
        Korisnik entity = new Korisnik();
        entity.setEmail(dto.getEmail());
        entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setLozinka(dto.getLozinka());
        entity.setPrezime(dto.getPrezime());
        entity.setTelefon(dto.getTelefon());
        entity.setUloga(dto.getUloga());
        return entity;
    }
    
}
