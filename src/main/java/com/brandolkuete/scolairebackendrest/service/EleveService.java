package com.brandolkuete.scolairebackendrest.service;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.dto.mapper.AbstractMapper;
import com.brandolkuete.scolairebackendrest.dto.mapper.EleveMapper;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.repository.EleveRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EleveService extends AbstractService<Eleve,EleveDTO> {


    private final EleveRepository eleveRepository;
    private final EleveMapper eleveMapper;

    public EleveService(EleveRepository eleveRepository, EleveMapper eleveMapper) {
        this.eleveRepository = eleveRepository;
        this.eleveMapper = eleveMapper;
    }

    public EleveDTO update(EleveDTO entityDTO, Long id) {

            return Optional.of(eleveRepository.findById(id))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(eleve -> {
                        eleve.setFiliere(entityDTO.getFiliere());
                        eleve.setNiveau(entityDTO.getNiveau());
                        eleve.setAddresse(entityDTO.getAddresse());
                        eleve.setMatricule(entityDTO.getMatricule());
                        eleve.setNom(entityDTO.getNom());
                        eleve.setPrenom(entityDTO.getPrenom());
                        eleve.setDate_nais(entityDTO.getDate_nais());
                        eleve= eleveRepository.save(eleve);
                        return eleve;
                    }).map(eleveMapper::toDto).get();
    }

    public Eleve findByMatricule(String matricule) {
        return eleveRepository.findByMatricule(matricule);
    }

    @Override
    protected JpaRepository<Eleve, Long> getRepository() {
        return eleveRepository;
    }

    @Override
    protected AbstractMapper<Eleve, EleveDTO> getMapper() {
        return eleveMapper;
    }
}