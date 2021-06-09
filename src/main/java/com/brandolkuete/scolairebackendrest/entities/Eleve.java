package com.brandolkuete.scolairebackendrest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Eleve extends Personne {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	private String niveau;
	private String filiere;

	public Eleve(Long id, String matricule, String nom, String prenom, Date date_nais, String addresse, String niveau, String filiere) {
		super(id, matricule, nom, prenom, date_nais, addresse);
		this.niveau = niveau;
		this.filiere = filiere;
	}

}