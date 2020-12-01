package com.brandolkuete.scolairebackendrest.config;


import com.brandolkuete.scolairebackendrest.entities.User;
import com.brandolkuete.scolairebackendrest.entities.UserDetailsImpl;
import com.brandolkuete.scolairebackendrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return UserDetailsImpl.build(user);
	}

}
