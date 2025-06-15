package com.example.crafts.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role = "USER"; // Default role

    // --- UserDetails Implementation ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return email; // Username is the email
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify if you implement expiry logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify if you implement lockout logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify if you implement credential expiry
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify if you implement soft-delete or bans
    }
}
