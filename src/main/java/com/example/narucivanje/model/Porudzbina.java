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
public class Porudzbina extends DomainEntity{
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
    //ovde imas jedan problem a to je sto je automatski fetch style = lazy a to znaci da
    //kada povucem porudzbine nece povuci i stavke pa ako zelim da vidim stavke necu moci ali 
    //to ne treba resiti tako sto pisemo fetchType = eager jer to je losa praksa jer onda pri svakom pozivu gde nam trebaju i samo
    //korisnici vucemo adrese e pa zato tamo u repo kod findall povise pieses @Query("Select k from korisnik k JOIN FETCH k.adrese
    
    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaPorudzbine> stavke = new ArrayList<>();
    public Porudzbina(LocalDateTime datumVreme, StatusPorudzbine status, String adresaDostave, BigDecimal ukupnaCena, Korisnik korisnik, Restoran restoran) {
       
        this.datumVreme = datumVreme;
        this.status = status;
        this.adresaDostave = adresaDostave;
        this.ukupnaCena = ukupnaCena;
        this.korisnik = korisnik;
        this.restoran = restoran;
    } 
}
