package br.com.zupacademy.kleysson.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String login;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 6)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {}

    public Usuario(@NotBlank String login, @NotBlank @Length(min = 6) SenhaLimpa senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha.getPassword());
    }
}
