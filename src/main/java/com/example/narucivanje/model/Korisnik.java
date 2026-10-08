/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
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


public class Korisnik extends DomainEntity{
    @Column(nullable = false)
    private String ime;
    @Column(nullable = false)
    private String prezime;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String lozinka;
    private String telefon;
    @Enumerated(EnumType.STRING)//ovo pisemo da bi se u bazi cuvalo kao slova enumi a ne kao brojevi...
    @Column(nullable = false)
    private Uloga uloga;

   
    public Korisnik(String ime, String prezime, String email, String lozinka, String telefon, Uloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.telefon = telefon;
        this.uloga = uloga;
    }
}
