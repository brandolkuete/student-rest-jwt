package com.brandolkuete.scolairebackendrest.repository;

import com.brandolkuete.scolairebackendrest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	Optional<User> findOneByUsername(String username);
}
