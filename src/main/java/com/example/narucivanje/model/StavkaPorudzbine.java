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
public class StavkaPorudzbine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int kolicina;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cena;
    @ManyToOne(optional = false)
    private Jelo jelo;
    @ManyToOne(optional = false)
    private Porudzbina porudzbina;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine( int kolicina, BigDecimal cena, Jelo jelo) {
     
        this.kolicina = kolicina;
        this.cena = cena;
        this.jelo = jelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Jelo getJelo() {
        return jelo;
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }
    
}
