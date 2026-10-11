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
public class Restoran extends DomainEntity{
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private String adresa;
    @Column(nullable = false)
    private String telefon;
    private String radnoVreme;

    
    public Restoran(String naziv, String adresa, String telefon, String radnoVreme) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.telefon = telefon;
        this.radnoVreme = radnoVreme;
    }
    
    
}
