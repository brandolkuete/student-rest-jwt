package com.brandolkuete.scolairebackendrest.repository;

import com.brandolkuete.scolairebackendrest.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository<T extends Personne> extends JpaRepository<Personne, Long>{
}
