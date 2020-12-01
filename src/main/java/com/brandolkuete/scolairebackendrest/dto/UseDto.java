package com.brandolkuete.scolairebackendrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UseDto {
	
	private String username;
	private String password;
}
