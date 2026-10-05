package com.example.narucivanje.service;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.example.narucivanje.dto.JeloDto;
import com.example.narucivanje.model.Jelo;
import com.example.narucivanje.model.Kategorija;
import com.example.narucivanje.model.Restoran;
import com.example.narucivanje.repository.JeloRepository;
import com.example.narucivanje.repository.KategorijaRepository;
import com.example.narucivanje.repository.RestoranRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import jdk.javadoc.doclet.Reporter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author necao
 */
@Service
public class JeloService {
    private final JeloRepository repo;
    private final RestoranRepository repoRestoran;
    private final KategorijaRepository repoKategorija;
    
    public JeloService(JeloRepository repo, RestoranRepository repoRestoran, KategorijaRepository repoKategorija) {
        this.repo = repo;
        this.repoRestoran = repoRestoran;
        this.repoKategorija = repoKategorija;
    }
//      List<Jelo> findByRestoranId(Long restoranId);
//    List<Jelo> findByRestoranIdAndDostupnostTrue(Long restoranId);
//    List<Jelo> findByKategorijaId(Long kategorijaId);
//    List<Jelo> findByNazivContainingIgnoreCase(String naziv);
//    List<Jelo> findByCenaLessThanEqual(BigDecimal cena);
//    List<Jelo> findByRestoranIdAndKategorijaId(Long restoranId, Long kategorijaId);
//    List<Jelo> findByRestoranIdAndKategorijaIdAndDostupnostTrue(Long restoranId, Long kategorijaId);
    @Transactional
    public JeloDto kreirajJelo(JeloDto dto)
    {
        Restoran restoran = repoRestoran.findById(dto.getRestoranId()).orElseThrow(() -> new EntityNotFoundException("Restoran sa Id: " + dto.getRestoranId() + " nije pronadjen!"));
        Kategorija kategorija = repoKategorija.findById(dto.getKategorijaId()).orElseThrow(() -> new EntityNotFoundException("Kategorija sa id: " + dto.getKategorijaId() + "nije pronadjena!"));
        
        if(repo.existsByNazivAndRestoranId(dto.getNaziv(), dto.getRestoranId()))
        {
            throw new IllegalArgumentException("Jelo sa nazivom '" + dto.getNaziv() +"' vec postoji!");
        }
        //ovde dto mapiraj u entity i samo lagani save
        
    }
//    public Jelo findById(Long jeloId)
//    {
//        return repo.findById(jeloId).orElseThrow(() -> new EntityNotFoundException("Nema jela sa id: " + jeloId));
//    }
//    public List<Jelo> findByRestoranId(Long restoranId)
//    {
//        if(!repoRestoran.existsById(restoranId))
//        {
//            throw new EntityNotFoundException("Restoran sa id-em: " + restoranId + " ne postoji");
//        }
//        return repo.findByRestoranId(restoranId);
//    }
////    public List<Jelo> findByNazivContainingIgnoreCase(String naziv)
////    {
////        return repo.findByNazivContainingIgnoreCase(naziv);
////    }
//    public List<Jelo> findByRestoranIdAndDostupnostTrue(Long restoranId)
//    {
//        if(!repoRestoran.existsById(restoranId))
//        {
//           throw new EntityNotFoundException("Restoran sa id-em: " + restoranId + " ne postoji");
//        }
//        return repo.findByRestoranIdAndDostupnostTrue(restoranId);
//    }
    
}
