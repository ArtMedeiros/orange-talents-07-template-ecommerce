package br.com.zupacademy.kleysson.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 6)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    private Usuario() {}

    public Usuario(@NotBlank String email, @NotBlank @Length(min = 6) SenhaLimpa senha) {
        Assert.hasLength(email, "O email não pode estar vazio");
        Assert.notNull(senha, "A senha não pode ser nula");

        this.email = email;
        this.senha = senha.getSenha();
        this.dataCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
