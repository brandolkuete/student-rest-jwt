package com.brandolkuete.scolairebackendrest.rest;

import com.brandolkuete.scolairebackendrest.dto.TokenDto;
import com.brandolkuete.scolairebackendrest.dto.UseDto;
import com.brandolkuete.scolairebackendrest.entities.UserForm;
import com.brandolkuete.scolairebackendrest.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scolairerest/users")
@CrossOrigin("http://localhost:3000")
public class UserRessources {

	@Autowired
	private UserService userService;


	@PostMapping(value= "/signin")
	public ResponseEntity<TokenDto> login(@RequestBody UseDto userDto) {
		TokenDto reponse= new TokenDto(userService.signin(userDto));
		return new  ResponseEntity<>( reponse,HttpStatus.OK) ;
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserForm user) {
		return  new  ResponseEntity<>( userService.signup(user),HttpStatus.OK);
	}
}
