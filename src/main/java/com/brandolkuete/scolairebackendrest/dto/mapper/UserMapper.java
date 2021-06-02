package com.brandolkuete.scolairebackendrest.dto.mapper;

import com.brandolkuete.scolairebackendrest.entities.Role;
import com.brandolkuete.scolairebackendrest.entities.User;
import com.brandolkuete.scolairebackendrest.entities.UserForm;
import com.brandolkuete.scolairebackendrest.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserMapper implements EntityMapper<User,UserForm> {
    private static final ModelMapper modelMapper= new ModelMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User toEntity(UserForm userForm) {
        User user= modelMapper.map(userForm, User.class);

        user.setUsername(userForm.getUsername());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        if (!roleRepository.existsByRole("ADMIN")){
            roleRepository.save(new Role("ADMIN"));
        }
        if (!roleRepository.existsByRole("USER")){
            roleRepository.save(new Role("USER"));
        }

        List<Role> roles= new ArrayList<>();
        Role role1= roleRepository.findByRole("ADMIN");
        Role role2= roleRepository.findByRole("USER");
        roles.add(role1);
        roles.add(role2);

        user.setRoles(roles);
        return user;
    }

    @Override
    public UserForm toDto(User user) {
        return null;
    }
}
