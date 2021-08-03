package br.com.zupacademy.kleysson.mercadolivre.model;

import io.jsonwebtoken.lang.Assert;
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
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 6)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @Deprecated
    public Usuario() {}

    public Usuario(@NotBlank String email, @NotBlank @Length(min = 6) SenhaLimpa senha) {
        Assert.hasLength(email, "O email não pode estar vazio");
        Assert.notNull(senha, "A senha não pode ser nula");

        this.email = email;
        this.senha = senha.getSenha();
        this.dataCadastro = LocalDateTime.now();
    }
}
