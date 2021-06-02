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
public class PersonneDTO extends AuditableDTO {

	private Long id;
	
	private String matricule;
	private String nom;
	private String prenom;
	private Date date_nais;
	private String addresse;
}
