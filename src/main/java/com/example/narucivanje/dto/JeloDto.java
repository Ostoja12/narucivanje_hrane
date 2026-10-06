/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
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
public class JeloDto extends DomainDto{

    private String naziv;
    private String opis;
    private BigDecimal cena;
    private boolean dostupnost;
    private String slikaUrl;
    
    
    private Long restoranId;
    private String restoranNaziv;
    
    private Long kategorijaId;
    private String kategorijaNaziv;
    public JeloDto(String naziv, String opis, BigDecimal cena, boolean dostupnost, String slikaUrl, Long restoranId, String restoranNaziv, Long kategorijaId, String kategorijaNaziv, Long id) {
        super(id);
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.dostupnost = dostupnost;
        this.slikaUrl = slikaUrl;
        this.restoranId = restoranId;
        this.restoranNaziv = restoranNaziv;
        this.kategorijaId = kategorijaId;
        this.kategorijaNaziv = kategorijaNaziv;
    }
    
}
