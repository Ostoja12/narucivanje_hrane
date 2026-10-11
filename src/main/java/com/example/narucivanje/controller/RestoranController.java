/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.controller;

import com.example.narucivanje.dto.RestoranDto;
import com.example.narucivanje.mapper.RestoranMapper;
import com.example.narucivanje.model.Restoran;
import com.example.narucivanje.service.RestoranService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author necao
 */
@RestController
@RequestMapping("/api/restorani")
@RequiredArgsConstructor
public class RestoranController {
    private final RestoranService restoranService;
    private final RestoranMapper mapper;
    @GetMapping()
    public ResponseEntity<List<RestoranDto>> uzmiSveRestorane()
    {
        return ResponseEntity.ok(restoranService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestoranDto> uzmiRestoran(@PathVariable Long id)
    {
        return ResponseEntity.ok(restoranService.searchById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<RestoranDto>> pretraziRestoraneIme(@RequestParam String ime)
    {
        return ResponseEntity.ok(restoranService.searchByName(ime));
    }
    @PostMapping("/save")
    public ResponseEntity<RestoranDto> sacuvajRestoran(@RequestBody RestoranDto restoranDto)
    {
        RestoranDto restoran = restoranService.addRestoran(restoranDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(restoranService.addRestoran(restoranDto));
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiRestoran(@PathVariable Long id)
    {
        restoranService.removeRestoran(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestoranDto> izmeniRestoran(@PathVariable Long id, @RequestBody RestoranDto restoranNovi)
    {
        return ResponseEntity.ok(restoranService.izmeniRestoran(id, restoranNovi));
    }
}
