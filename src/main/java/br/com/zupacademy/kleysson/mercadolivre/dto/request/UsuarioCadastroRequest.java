package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.config.validation.Unique;
import br.com.zupacademy.kleysson.mercadolivre.model.SenhaLimpa;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioCadastroRequest {

    @NotBlank
    @Email
    @Unique(field = "email", entity = Usuario.class)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public Usuario converter() {
        return new Usuario(email, new SenhaLimpa(senha));
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
