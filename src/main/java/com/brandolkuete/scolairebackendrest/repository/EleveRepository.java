package com.brandolkuete.scolairebackendrest.repository;

import com.brandolkuete.scolairebackendrest.entities.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
	
	Eleve findByMatricule(String matricule);

	Optional<Eleve> findById(Long id);
}
