package com.brandolkuete.scolairebackendrest.repository;

import com.brandolkuete.scolairebackendrest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	User findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}
