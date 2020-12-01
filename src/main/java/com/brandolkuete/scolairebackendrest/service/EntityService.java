package com.brandolkuete.scolairebackendrest.service;

import java.text.ParseException;
import java.util.List;

public interface EntityService<DT0,ID> {
    DT0 save(DT0 entityDTO) throws ParseException;
    DT0 update(DT0 entityDTO, Long id);
    List<DT0> findAll();
    void delete(Long id);
    DT0 getOne(Long id);

}
