/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.narucivanje.service;

import com.example.narucivanje.dto.GeminiRequest;
import com.example.narucivanje.dto.GeminiResponse;
import com.example.narucivanje.dto.PreporukaOdgovorDto;
import com.example.narucivanje.dto.PreporukaZahtevDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author necao
 */
@Service
@RequiredArgsConstructor
public class AiRecommendationService {
    
    private final RestClient restClient = RestClient.create();
    
    @Value("${gemini.api.key}")
    private String apiKey;
    
    @Value("${gemini.api.url}")
    private String apiUrl;
    
    public PreporukaOdgovorDto generisiPreporuku(PreporukaZahtevDto zahtev) {
        try {
            return pozoviExternalAiApi(zahtev);
        } catch (Exception e) {
            e.printStackTrace();
            return generisiFallbackPreporuku(zahtev);
        }
    }

    private PreporukaOdgovorDto pozoviExternalAiApi(PreporukaZahtevDto zahtev) {
        String artikliStr = String.join(", ", zahtev.getNaziviArtikalaUKorpi());
        String prompt = "Korisnik je u restoranu narucio sledeca jela: [" + artikliStr + "]."
                + " Kao kulinarski strucnjak, preporuci mu 2 komplementarna jela iz jelovnika koja se odlicno uklapaju uz ovo." 
                + " Vrati odgovor iskljucivo u sledecem formatu, bez dodatnog teksta: ID_JELA: 2, 3 | Razlog: tvoj kratak i privlacan opis zasto se slaze"; 
        
        
        String urlSaKljucem = apiUrl + "?key=" + apiKey;
        
        GeminiRequest request = new GeminiRequest(prompt);
        
        GeminiResponse response = restClient.post()
                .uri(urlSaKljucem)
                .header("Content-Type", "application/json")
                .body(request)
                .retrieve()
                .body(GeminiResponse.class);
        
        
        String aiTekst = response != null ? response.getTekstOdgovora() : "Nema preporuke";
        
    
        PreporukaOdgovorDto odgovor = new PreporukaOdgovorDto();
        odgovor.setRazlogPreporuke(aiTekst); 
        
        return odgovor;
    }

    private PreporukaOdgovorDto generisiFallbackPreporuku(PreporukaZahtevDto zahtev) {
        
        PreporukaOdgovorDto fallback = new PreporukaOdgovorDto();
        fallback.setRazlogPreporuke("Trenutno nismo u mogućnosti da obradimo AI preporuku.");
        return fallback;
    }
}