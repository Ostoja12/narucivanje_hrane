/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.controller;

import com.example.narucivanje.dto.KategorijaDto;
import com.example.narucivanje.service.KategorijaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author necao
 */
@RestController
@RequestMapping("/api/kategorije")
@RequiredArgsConstructor
public class KategorijaController {
    private final KategorijaService service;

 
    @GetMapping
    public ResponseEntity<List<KategorijaDto>> findAll()
    {
        List<KategorijaDto> kategorije = service.findAll();
        return ResponseEntity.ok(kategorije);
    }
    @GetMapping("/{id}")
    public ResponseEntity<KategorijaDto> findById(@PathVariable Long id)
    {
        KategorijaDto kategorija = service.findById(id);
        return ResponseEntity.ok(kategorija);
    }
    @PostMapping
    public ResponseEntity<KategorijaDto> kreiraj(@RequestBody KategorijaDto kategorija)
    {
        KategorijaDto novaK = service.kreiraj(kategorija);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<KategorijaDto> izmeni(@PathVariable Long id, @RequestBody KategorijaDto nova)
    {
        KategorijaDto izmenjena = service.izmeni(id, nova);
        return ResponseEntity.ok(izmenjena);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id)
    {
        service.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
