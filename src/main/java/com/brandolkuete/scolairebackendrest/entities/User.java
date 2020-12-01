package com.brandolkuete.scolairebackendrest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns
          = @JoinColumn(name = "user_id",
          referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id",
                  referencedColumnName = "id"))
  private List<Role> roles;
}
