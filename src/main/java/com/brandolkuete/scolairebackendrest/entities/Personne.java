package com.brandolkuete.scolairebackendrest.entities;

import javax.persistence.*;

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
public class Personne extends Auditable{
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	private String matricule;
	private String nom;
	private String prenom;
	private Date date_nais;
	private String addresse;

	public Personne(Long id, String matricule, String nom, String prenom, String addresse) {
		this.id = id;
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.addresse = addresse;
	}

	public Personne(String matricule, String nom, String prenom, Date date_nais, String addresse) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.date_nais = date_nais;
		this.addresse = addresse;
	}
}
