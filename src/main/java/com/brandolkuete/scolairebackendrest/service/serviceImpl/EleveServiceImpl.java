package com.brandolkuete.scolairebackendrest.service.serviceImpl;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.dto.mapper.EleveMapper;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.repository.EleveRepository;
import com.brandolkuete.scolairebackendrest.service.EleveService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EleveServiceImpl implements EleveService {

    private final EleveRepository eleveRepository;

    private final EleveMapper eleveMapper;

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    public EleveServiceImpl(EleveRepository eleveRepository, EleveMapper eleveMapper) {
        this.eleveRepository = eleveRepository;
        this.eleveMapper = eleveMapper;
    }

    @Override
    public EleveDTO save(EleveDTO entityDTO) throws ParseException {
       final Eleve eleve = eleveMapper.toEntity(entityDTO);
        return eleveMapper.toDto(eleveRepository.save(eleve));
    }

    @Override
    public EleveDTO update(EleveDTO entityDTO, Long id) {

            return Optional.of(eleveRepository.findById(id))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(eleve -> {
                        eleve.setFiliere(entityDTO.getFiliere());
                        eleve.setNiveau(entityDTO.getNiveau());
                        eleve.setAddresse(entityDTO.getAddresse());
                        try {
                            eleve.setDate_nais(dateFormat.parse(entityDTO.getDate_nais()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        eleve.setMatricule(entityDTO.getMatricule());
                        eleve.setNom(entityDTO.getNom());
                        eleve.setPrenom(entityDTO.getPrenom());

                        eleve= eleveRepository.save(eleve);
                        return eleve;
                    }).map(eleveMapper::toDto).get();
    }

    @Override
    public List<EleveDTO> findAll() {
        return eleveRepository.findAll().stream().map(eleveMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Eleve eleve = Optional.of(eleveRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get).get();
        eleveRepository.delete(eleve);
    }

    @Override
    public EleveDTO getOne(Long id) {
        return Optional.of(eleveRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(eleveMapper::toDto)
                .get();
    }

    @Override
    public Eleve findByMatricule(String matricule) {
        return eleveRepository.findByMatricule(matricule);
    }
}