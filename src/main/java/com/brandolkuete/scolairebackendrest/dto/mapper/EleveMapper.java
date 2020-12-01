package com.brandolkuete.scolairebackendrest.dto.mapper;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EleveMapper implements EntityMapper<Eleve,EleveDTO> {
    private static final ModelMapper modelMapper= new ModelMapper();
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Eleve toEntity(EleveDTO eleveDTO) throws ParseException {
        Eleve eleve= modelMapper.map(eleveDTO, Eleve.class);
        eleve.setDate_nais(dateFormat.parse(eleveDTO.getDate_nais()));
        return eleve;
    }

    @Override
    public EleveDTO toDto(Eleve eleve) {
        EleveDTO eleveDTO= modelMapper.map(eleve,EleveDTO.class);
        eleveDTO.setDate_nais(dateFormat.format(eleve.getDate_nais()));
        return eleveDTO;
    }

    @Override
    public List<EleveDTO> toEntity(List<Eleve> eleves) {
        return null;
    }

    @Override
    public List<Eleve> toDto(List<EleveDTO> eleveDTOS) {
        return null;
    }
}
