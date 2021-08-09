package com.brandolkuete.scolairebackendrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EleveDTO extends PersonneDTO{
	
	private String niveau;
	private String filiere;

	public EleveDTO(Long id, String matricule, String nom, String prenom, Date date_nais, String addresse, String niveau, String filiere) {
		super(id, matricule, nom, prenom, date_nais, addresse);
		this.niveau = niveau;
		this.filiere = filiere;
	}

	public EleveDTO(String matricule, String nom, String prenom, String addresse, String niveau, String filiere) {
		super(matricule, nom, prenom, addresse);
		this.niveau = niveau;
		this.filiere = filiere;
	}

}
