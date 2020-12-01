package com.brandolkuete.scolairebackendrest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserForm {

    private String username;
    private String password;
    private String confirmPassword;
}
