package com.brandolkuete.scolairebackendrest.repository;


import com.brandolkuete.scolairebackendrest.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(String role);
    boolean existsByRole(String role);
}
