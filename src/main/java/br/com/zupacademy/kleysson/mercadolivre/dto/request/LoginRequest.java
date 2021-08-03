package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.*;

public class LoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
