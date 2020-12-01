package com.brandolkuete.scolairebackendrest.repository;

import com.brandolkuete.scolairebackendrest.entities.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
	
	public Eleve findByMatricule(String matricule);
}
