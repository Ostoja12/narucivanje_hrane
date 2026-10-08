/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.controller;

import com.example.narucivanje.dto.JeloDto;
import com.example.narucivanje.service.JeloService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author necao
 */
@RestController
@RequestMapping("/api/jela")
@RequiredArgsConstructor //zbog final jeloService, jer spring pravi bean tj ulazi odma u konstruktor a ovaj u konstruktor ubaci samo final
public class JeloController {
    
    private final JeloService jeloService;
    
    //GET /api/jela vracamo sva jela
    @GetMapping("/pretrazi")
    public ResponseEntity<Page<JeloDto>> pretraziJela(@RequestParam(required = false)Long restoranId, 
            @RequestParam(required = false)Long kategorijaId,
            @RequestParam(required = false)BigDecimal maxCena,
            @RequestParam(required = false) String naziv,
            Pageable pageable)
    {
       Page<JeloDto> rezultati = jeloService.pretraziJela(restoranId, kategorijaId, maxCena, naziv, pageable);
       return ResponseEntity.ok(rezultati);
    }
    
    @GetMapping("/restoran/{restoranId}")
    public ResponseEntity<List<JeloDto>> getJelaPoRestoranu(@PathVariable Long restoranId)
    {
        List<JeloDto> rezultati = jeloService.findByRestoranId(restoranId);
        return ResponseEntity.ok(rezultati);    
    }
    @GetMapping("/{jeloId}")
    public ResponseEntity<JeloDto> getById(@PathVariable Long jeloId)
    {
        JeloDto jeloDto = jeloService.getById(jeloId);
        return ResponseEntity.ok(jeloDto);
    }
    @PostMapping
    public ResponseEntity<JeloDto> save(@RequestBody JeloDto jeloDto)
    {
        JeloDto novoJelo = jeloService.kreirajJelo(jeloDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJelo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id)
    {     
        jeloService.obrisiJelo(id);
        return ResponseEntity.noContent().build(); //noContent postavlja status na 204 a build se koristi kada odgovor nema telo
    }
    @PutMapping("/{id}")
    public ResponseEntity<JeloDto> update(@PathVariable Long id, @RequestBody JeloDto jeloDto)
    {
        JeloDto izmenjenoJeloDto = jeloService.izmeniJelo(id, jeloDto);
        return ResponseEntity.ok(izmenjenoJeloDto);
    }
    
}
