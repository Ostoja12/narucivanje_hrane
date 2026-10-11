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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author necao
 */

@Entity
@Data
@NoArgsConstructor

public class StavkaPorudzbine extends DomainEntity{

    @Column(nullable = false)
    private int kolicina;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cena;
    @ManyToOne(optional = false)
    private Jelo jelo;
    @ManyToOne(optional = false)
    private Porudzbina porudzbina;


    public StavkaPorudzbine( int kolicina, BigDecimal cena, Jelo jelo) {
     
        this.kolicina = kolicina;
        this.cena = cena;
        this.jelo = jelo;
    }
}
