package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.*;

public class LoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
