/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

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
public class KategorijaDto extends DomainDto{
    private String naziv;
}
