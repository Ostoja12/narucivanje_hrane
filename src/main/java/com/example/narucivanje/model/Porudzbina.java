/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author necao
 */
@Entity
public class Porudzbina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime datumVreme;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPorudzbine status;
    @Column(nullable = false)
    private String adresaDostave;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ukupnaCena;
    @ManyToOne(optional = false)
    private Korisnik korisnik;
    @ManyToOne(optional = false)
    private Restoran restoran;
    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaPorudzbine> stavke = new ArrayList<>();
    public Porudzbina() {
    }

    public Porudzbina(LocalDateTime datumVreme, StatusPorudzbine status, String adresaDostave, BigDecimal ukupnaCena, Korisnik korisnik, Restoran restoran) {
       
        this.datumVreme = datumVreme;
        this.status = status;
        this.adresaDostave = adresaDostave;
        this.ukupnaCena = ukupnaCena;
        this.korisnik = korisnik;
        this.restoran = restoran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public StatusPorudzbine getStatus() {
        return status;
    }

    public void setStatus(StatusPorudzbine status) {
        this.status = status;
    }

    public String getAdresaDostave() {
        return adresaDostave;
    }

    public void setAdresaDostave(String adresaDostave) {
        this.adresaDostave = adresaDostave;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
    
    
}
