package com.brandolkuete.scolairebackendrest;

import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.entities.Role;
import com.brandolkuete.scolairebackendrest.entities.User;
import com.brandolkuete.scolairebackendrest.repository.EleveRepository;
import com.brandolkuete.scolairebackendrest.repository.RoleRepository;
import com.brandolkuete.scolairebackendrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class ScolaireBackendRestApplication {
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    public static void main(String[] args) {
        SpringApplication.run(ScolaireBackendRestApplication.class, args);
    }

   /* @Bean
    public CommandLineRunner integrationTestDataCreation(EleveRepository eleveRepository, RoleRepository roleRepository, UserRepository userRepository) throws ParseException {
        return (args ->{
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            userRepository.save(new User("brandol",passwordEncoder.encode("brandol20"),Arrays.asList(roleRepository.findByRole("ADMIN"),roleRepository.findByRole("USER"))));

            eleveRepository.save(new Eleve("15T2778", "Kuete Melong", "Brandol", new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-04"), "Yaoundé", "Master 1","Informatique"));
            eleveRepository.save(new Eleve("1000UV4", "Atangana", "Paul", new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-06"), "Yaoundé", "Licence 1","Chimie"));
            eleveRepository.save(new Eleve("2000UV4", "Talla", "Jean", new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-07"), "Douala", "Licence 2","Mathématiques"));
        });
    }*/
}
