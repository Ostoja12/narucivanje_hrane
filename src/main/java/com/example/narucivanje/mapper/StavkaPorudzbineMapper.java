/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.mapper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.example.narucivanje.dto.StavkaPorudzbineDto;
import com.example.narucivanje.model.Jelo;
import com.example.narucivanje.model.StavkaPorudzbine;
import com.example.narucivanje.repository.JeloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author necao
 */
@Component
public class StavkaPorudzbineMapper implements BaseMapper<StavkaPorudzbine, StavkaPorudzbineDto> {

    @Autowired
    private JeloRepository jeloRepo;

    @Override
    public StavkaPorudzbineDto toDto(StavkaPorudzbine entity) {
        if (entity == null) {
            return null;
        }
        StavkaPorudzbineDto dto = new StavkaPorudzbineDto();
        dto.setId(entity.getId());
        dto.setCena(entity.getCena());
        dto.setKolicina(entity.getKolicina()); // <--- Ispravljeno ovde!
        
        if (entity.getJelo() != null) {
            dto.setIdJelo(entity.getJelo().getId());
        }
        return dto;
    }

    @Override
    public StavkaPorudzbine toEntity(StavkaPorudzbineDto dto) {
        if (dto == null) {
            return null;
        }
        
        StavkaPorudzbine entity = new StavkaPorudzbine();
        entity.setId(dto.getId());
        entity.setCena(dto.getCena());
        entity.setKolicina(dto.getKolicina());
        
        // KLJUČNO: Pronađi jelo preko ID-ja iz DTO-a i ubaci ga u entitet!
        if (dto.getIdJelo() != null) {
            Jelo jelo = jeloRepo.findById(dto.getIdJelo())
                    .orElseThrow(() -> new EntityNotFoundException("Jelo sa id: " + dto.getIdJelo() + " nije pronadjeno"));
            entity.setJelo(jelo);
        }
        
        return entity;
    }
}



