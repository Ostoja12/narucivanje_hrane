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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author necao
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

   
   
    public Jelo(String naziv, String opis, boolean dostupnost, BigDecimal cena, Restoran restoran, Kategorija kategorija) {
        this.naziv = naziv;
        this.opis = opis;
        this.dostupnost = dostupnost;
        this.cena = cena;
        this.restoran = restoran;
        this.kategorija = kategorija;
    } 
}
