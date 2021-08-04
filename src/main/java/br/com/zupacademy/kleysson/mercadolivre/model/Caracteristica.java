package br.com.zupacademy.kleysson.mercadolivre.model;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Caracteristica {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private Caracteristica () {}

    public Caracteristica(String nome, String descricao) {
        Assert.hasLength(nome, "O nome não pode estar vazio");
        Assert.hasLength(descricao, "A descrição não pode estar vazio");

        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
