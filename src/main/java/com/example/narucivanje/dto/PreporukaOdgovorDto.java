/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author necao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreporukaOdgovorDto {
    private List<String> preporucenaJela;
    private String razlogPreporuke;
    private Double pouzdanost;
}
