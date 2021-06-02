package com.brandolkuete.scolairebackendrest.service.Impl;

import com.brandolkuete.scolairebackendrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }else {
            UserDetails userSec= (UserDetails) authentication.getPrincipal();
            Optional<com.brandolkuete.scolairebackendrest.entities.User> user = userRepository.findOneByUsername(userSec.getUsername());

            if (!user.isPresent()){
                return Optional.empty();
            }else {
                com.brandolkuete.scolairebackendrest.entities.User user1= user.get();
                return Optional.of(user1.getUsername());
            }
        }
    }
}
