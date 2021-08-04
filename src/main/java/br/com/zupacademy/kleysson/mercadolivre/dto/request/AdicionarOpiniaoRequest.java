package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.model.Opiniao;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdicionarOpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdicionarOpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao converter(Produto produto, Usuario usuario){
        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }
}
