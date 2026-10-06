/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import com.example.narucivanje.dto.JeloDto;
import com.example.narucivanje.model.Jelo;

/**
 *
 * @author necao
 */
public class JeloMapper implements BaseMapper<Jelo, JeloDto>{

    @Override
    public JeloDto toDto(Jelo entity) {
        if(entity == null)
            return null;
        JeloDto dto = new JeloDto();
        dto.setId(entity.getId());
        dto.setCena(entity.getCena());
        dto.setDostupnost(entity.isDostupnost());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());
        if(entity.getKategorija()!= null)
        {
            dto.setKategorijaId(entity.getKategorija().getId());
            dto.setKategorijaNaziv(entity.getKategorija().getNaziv());   
        }
        
        if(entity.getRestoran()!= null)
        {
            dto.setRestoranId(entity.getRestoran().getId());
            dto.setRestoranNaziv(entity.getRestoran().getNaziv());
        }
        return dto;
        
    }

    @Override
    public Jelo toEntity(JeloDto dto) {
        if(dto == null)
            return null;
        Jelo jelo = new Jelo();
        jelo.setId(dto.getId());
        jelo.setDostupnost(dto.isDostupnost());
        jelo.setCena(dto.getCena());
        jelo.setOpis(dto.getOpis());
        
        return jelo;
        
    }
    
}
