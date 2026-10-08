/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

import com.example.narucivanje.model.Uloga;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author necao
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KorisnikDto extends DomainDto{
    //String ime, String prezime, String email, String lozinka, String telefon, Uloga uloga
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String telefon;
    private Uloga uloga;

    public KorisnikDto(String ime, String prezime, String email, String lozinka, String telefon, Uloga uloga, Long id) {
        super(id);
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.telefon = telefon;
        this.uloga = uloga;
    }
    
}
