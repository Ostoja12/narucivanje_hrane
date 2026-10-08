/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.mapper;

import com.example.narucivanje.dto.DomainDto;
import com.example.narucivanje.dto.KategorijaDto;
import com.example.narucivanje.model.Kategorija;
import org.springframework.stereotype.Component;

/**
 *
 * @author necao
 */
@Component
public class KategorijaMapper implements BaseMapper<Kategorija, KategorijaDto>{

    @Override
    public KategorijaDto toDto(Kategorija entity) {
        if(entity == null)
            return null;
        KategorijaDto kategorijaDto = new KategorijaDto();
        kategorijaDto.setId(entity.getId());
        kategorijaDto.setNaziv(entity.getNaziv());
        return kategorijaDto;
    }

    @Override
    public Kategorija toEntity(KategorijaDto dto) {
        if(dto == null)
            return null;
        Kategorija entity = new Kategorija();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        return entity;
    }
    
    
}
