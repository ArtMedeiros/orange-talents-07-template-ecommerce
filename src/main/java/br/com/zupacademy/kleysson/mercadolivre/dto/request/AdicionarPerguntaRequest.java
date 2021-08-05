package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.config.email.Email;
import br.com.zupacademy.kleysson.mercadolivre.model.Pergunta;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class AdicionarPerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdicionarPerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta converter(Usuario interessado, Produto produto) {
        return new Pergunta(titulo, interessado, produto);
    }
}
