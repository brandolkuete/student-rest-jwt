package com.brandolkuete.scolairebackendrest.service;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.entities.Eleve;

public interface EleveService extends EntityService<EleveDTO, Long> {
    public Eleve findByMatricule(String matricule);
}
