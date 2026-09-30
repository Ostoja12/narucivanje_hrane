/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author necao
 */
@Entity
public class Restoran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private String adresa;
    @Column(nullable = false)
    private String telefon;
    private String radnoVreme;

    public Restoran() {
    }

    public Restoran(String naziv, String adresa, String telefon, String radnoVreme) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.telefon = telefon;
        this.radnoVreme = radnoVreme;
    }

    public String getAdresa() {
        return adresa;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    

    public String getTelefon() {
        return telefon;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    
}
