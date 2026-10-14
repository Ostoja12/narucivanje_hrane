/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

import com.example.narucivanje.model.StatusPorudzbine;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class PorudzbinaDto extends DomainDto{
    LocalDateTime datumVreme;
    StatusPorudzbine status;
    String adresaDostave;
    BigDecimal ukupnaCena;
    Long idKorisnik;
    String imeKorisnika;
    String prezimeKorisnika;
    Long idRestoran;
    String nazivRestorana;
    private String aiPreporuka;
    private List<StavkaPorudzbineDto> stavke = new ArrayList<>();
    
    public PorudzbinaDto(LocalDateTime datumVreme, StatusPorudzbine status, String adresaDostave, BigDecimal ukupnaCena, Long idKorisnik, Long idRestoran, String imeKorisnika, String prezimeKorisnika ,String nazivRestorana, String aiPreporuka) {
       
        this.datumVreme = datumVreme;
        this.status = status;
        this.adresaDostave = adresaDostave;
        this.ukupnaCena = ukupnaCena;
        this.idKorisnik = idKorisnik;
        this.idRestoran = idRestoran;
        this.imeKorisnika = imeKorisnika;
        this.prezimeKorisnika = prezimeKorisnika;
        this.nazivRestorana = nazivRestorana;
        this.aiPreporuka = aiPreporuka;
    } 
}
