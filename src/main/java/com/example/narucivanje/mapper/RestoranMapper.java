/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.mapper;

import com.example.narucivanje.dto.RestoranDto;
import com.example.narucivanje.model.Restoran;
import org.springframework.stereotype.Component;

/**
 *
 * @author necao
 */
@Component
public class RestoranMapper implements BaseMapper<Restoran, RestoranDto>{

    @Override
    public RestoranDto toDto(Restoran entity) {
        if(entity == null)
        {
            return null;
        }
        RestoranDto dto = new RestoranDto();
        dto.setId(entity.getId());
        dto.setAdresa(entity.getAdresa());
        dto.setNaziv(entity.getNaziv());
        dto.setRadnoVreme(entity.getRadnoVreme());
        dto.setTelefon(entity.getTelefon());
        return dto;
    }

    @Override
    public Restoran toEntity(RestoranDto dto) {
        if(dto == null)
        {
            return null;
        }
        Restoran entity = new Restoran();
        entity.setAdresa(dto.getAdresa());
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setRadnoVreme(dto.getRadnoVreme());
        entity.setTelefon(dto.getTelefon());
        return entity;
    }
    
}
