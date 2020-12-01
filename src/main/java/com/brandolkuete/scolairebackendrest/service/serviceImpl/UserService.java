package com.brandolkuete.scolairebackendrest.service.serviceImpl;

import com.brandolkuete.scolairebackendrest.config.JwtTokenProvider;
import com.brandolkuete.scolairebackendrest.config.MyUserDetails;
import com.brandolkuete.scolairebackendrest.dto.UseDto;
import com.brandolkuete.scolairebackendrest.dto.mapper.UserMapper;
import com.brandolkuete.scolairebackendrest.entities.Role;
import com.brandolkuete.scolairebackendrest.entities.User;
import com.brandolkuete.scolairebackendrest.entities.UserDetailsImpl;
import com.brandolkuete.scolairebackendrest.entities.UserForm;
import com.brandolkuete.scolairebackendrest.exception.CustomException;
import com.brandolkuete.scolairebackendrest.repository.RoleRepository;
import com.brandolkuete.scolairebackendrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserMapper userMapper;


	public String signup(UserForm userForm) {
		if (!userForm.getPassword().equals(userForm.getConfirmPassword()))
			throw new CustomException("Vous devez confirmer le mot de passe",HttpStatus.UNPROCESSABLE_ENTITY);

		User user=  userRepository.findByUsername(userForm.getUsername());
		if (user != null) {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			User user1= userMapper.toEntity(userForm);
			userRepository.save(user1);

			return "Enregistrement réussi";
		}
	}

	public String signin(UseDto useDto) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(useDto.getUsername(), useDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token= jwtTokenProvider.createToken(authentication);

			/*UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();

			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());*/

			return token;
	}
}
