/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.controller;

import com.example.narucivanje.dto.KorisnikDto;
import com.example.narucivanje.service.KorisnikService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author necao
 */
@RestController
@RequestMapping("/api/korisnici")
@RequiredArgsConstructor
public class KorisnikController {
    private final KorisnikService korisnikService;
    @PostMapping("/login")
    public ResponseEntity<KorisnikDto> login(@RequestBody Map <String, String> request)
    {
        String email = request.get("email");
        String password = request.get("password");//rizicno jer mora i sa klijenta u jsonu da se posalje bas kao email i password
        
        return ResponseEntity.ok(korisnikService.login(email, password));
    }
    @PostMapping("/registracija")
    public ResponseEntity<KorisnikDto> registracija(@RequestBody KorisnikDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(korisnikService.registracija(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(korisnikService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<KorisnikDto>> getAll()
    {
        return ResponseEntity.ok(korisnikService.findAll());
    }
}
