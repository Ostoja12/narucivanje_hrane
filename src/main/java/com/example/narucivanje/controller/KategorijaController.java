/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.controller;

import com.example.narucivanje.model.Kategorija;
import com.example.narucivanje.service.KategorijaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author necao
 */
@RestController
@RequestMapping("/api/kategorije")
public class KategorijaController {
    private final KategorijaService service;

    public KategorijaController(KategorijaService service) {
        this.service = service;
    }
    @GetMapping
    public List<Kategorija> findAll()
    {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Kategorija findById(@PathVariable Long id)
    {
        return service.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kategorija kreiraj(@RequestBody Kategorija kategorija)
    {
        return service.kreiraj(kategorija);
    }
    
    @PutMapping("/{id}")
    public Kategorija izmeni(@PathVariable Long id, @RequestBody Kategorija nova)
    {
        return service.izmeni(id, nova);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void obrisi(@PathVariable Long id)
    {
        service.obrisi(id);
    }
}
