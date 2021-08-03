package br.com.zupacademy.kleysson.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    @NotBlank
    @Size(min = 6)
    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        Assert.hasLength(senha, "A senha não pode estar vazia");
        Assert.isTrue(senha.length() >= 6, "A senha deve conter no mínimo 6 caracteres");

        this.senha = senha;
    }

    public String getSenha() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
