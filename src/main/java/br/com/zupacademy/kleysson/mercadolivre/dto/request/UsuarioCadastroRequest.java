package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.utils.validation.Unique;
import br.com.zupacademy.kleysson.mercadolivre.model.SenhaLimpa;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

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

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioCadastroRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
