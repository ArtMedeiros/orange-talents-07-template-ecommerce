package br.com.zupacademy.kleysson.mercadolivre.model;

import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @ManyToOne
    private Usuario interessado;

    @NotNull
    @ManyToOne
    private Produto produto;

    private LocalDateTime dataCadastro;

    private Pergunta() {}

    public Pergunta(String titulo, Usuario interessado, Produto produto) {
        Assert.hasLength(titulo, "O título deve ser informado");
        Assert.notNull(interessado, "O usuário deve ser informado");
        Assert.notNull(produto, "O produto deve ser informado");

        this.titulo = titulo;
        this.interessado = interessado;
        this.produto = produto;
        this.dataCadastro = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getInteressado() {
        return interessado;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }
}