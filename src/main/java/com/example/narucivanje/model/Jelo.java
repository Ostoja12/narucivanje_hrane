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
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 *
 * @author necao
 */
@Entity
public class Jelo extends DomainEntity{
   
    @Column(nullable = false)
    private String naziv;
    private String opis;
    private boolean dostupnost;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cena;
    @ManyToOne(optional = false)
    private Restoran restoran;
    @ManyToOne(optional = false)
    private Kategorija kategorija;

    public Jelo() {
    }
   
    public Jelo(String naziv, String opis, boolean dostupnost, BigDecimal cena, Restoran restoran, Kategorija kategorija) {
        this.naziv = naziv;
        this.opis = opis;
        this.dostupnost = dostupnost;
        this.cena = cena;
        this.restoran = restoran;
        this.kategorija = kategorija;
    }

    

    public BigDecimal getCena() {
        return cena;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }
    
    


    public Kategorija getKategorija() {
        return kategorija;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    

    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }

    

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
    
    
}
