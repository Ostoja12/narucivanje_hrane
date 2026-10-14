/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

import java.math.BigDecimal;
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
public class StavkaPorudzbineDto extends DomainDto{
    private int kolicina;
    private BigDecimal cena;
    private Long idJelo;
}
