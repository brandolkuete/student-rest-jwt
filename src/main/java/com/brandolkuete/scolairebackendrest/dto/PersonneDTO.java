package com.brandolkuete.scolairebackendrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonneDTO {

	private Long id;
	
	private String matricule;
	private String nom;
	private String prenom;
	private String date_nais;
	private String addresse;
}
