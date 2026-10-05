/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author necao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JeloDto {
    private Long id;
    private String naziv;
    private String opis;
    private BigDecimal cena;
    private boolean dostupnost;
    private String slikaUrl;

    // Umesto celih objekata Restoran i Kategorija, nosimo samo ID i Naziv
    private Long restoranId;
    private String restoranNaziv;
    
    private Long kategorijaId;
    private String kategorijaNaziv;
}
