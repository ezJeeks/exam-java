package com.example.exam.domain.models.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_role")
public class AppRole implements GrantedAuthority {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name", length = 128, nullable = false)
    String name;

    @ManyToMany(mappedBy = "roles")
    Set<AppUser> users;
    @Override
    public String getAuthority() {
        return getName();
    }
    public AppRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
