package br.com.zupacademy.kleysson.mercadolivre.model;

import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    private Opiniao() {}

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        Assert.notNull(nota, "A nota deve ser preenchida");
        Assert.hasLength(titulo, "O título deve ser preenchido");
        Assert.hasLength(descricao, "A descricao deve ser preenchida");
        Assert.notNull(usuario, "O usuário é obrigatório");
        Assert.notNull(produto, "O produto é obrigatório");

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return nota.equals(opiniao.nota) && titulo.equals(opiniao.titulo) && descricao.equals(opiniao.descricao) && usuario.equals(opiniao.usuario) && produto.equals(opiniao.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, titulo, descricao, usuario, produto);
    }
}
