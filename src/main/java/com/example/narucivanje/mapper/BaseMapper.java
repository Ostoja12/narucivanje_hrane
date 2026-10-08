/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.narucivanje.mapper;

import com.example.narucivanje.dto.DomainDto;
import com.example.narucivanje.model.DomainEntity;

/**
 *
 * @author necao
 */
public interface BaseMapper <E extends DomainEntity, D extends DomainDto>{
    D toDto(E entity);
    E toEntity(D dto);
}
