/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.controller;

import com.example.narucivanje.dto.PorudzbinaDto;
import com.example.narucivanje.service.PorudzbinaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author necao
 */
@RestController
@RequestMapping("/api/porudzbine")
@RequiredArgsConstructor
public class PorudzbinaController {
    private final PorudzbinaService porudzbinaService;
    
    @GetMapping
    public ResponseEntity<List<PorudzbinaDto>> getAll()
    {
        List<PorudzbinaDto> porudzbine = porudzbinaService.getAll();
        return ResponseEntity.ok(porudzbine);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PorudzbinaDto> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(porudzbinaService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<PorudzbinaDto> add(@RequestBody PorudzbinaDto pDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(porudzbinaService.save(pDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        porudzbinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PorudzbinaDto> update(@RequestBody PorudzbinaDto pDto, @PathVariable Long id)
    {
        return ResponseEntity.ok(porudzbinaService.update(pDto, id));
    }
    
}
