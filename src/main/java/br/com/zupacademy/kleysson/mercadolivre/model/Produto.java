package br.com.zupacademy.kleysson.mercadolivre.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    @Positive
    private Double valor;

    @NotNull
    @Column(nullable = false)
    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @Size(min = 3)
    @Embedded
    @ElementCollection
    private Set<Caracteristica> caracteristicas;

    @ElementCollection
    private Set<String> imagens;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario dono;

    private LocalDateTime dataCadastro;

    private Produto() {}

    public Produto(String nome, Double valor, Integer quantidade, Set<Caracteristica> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        Assert.hasLength(nome, "O nome não pode estar vazio");
        Assert.notNull(valor, "O valor não pode estar vazio");
        Assert.isTrue(valor > 0, "O valor deve ser maior do que 0");
        Assert.notNull(quantidade, "A quantidade não pode estar vazio");
        Assert.isTrue(quantidade >= 0, "A quantidade deve ser positiva");
        Assert.isTrue(caracteristicas.size() >= 3, "A lista de características deve ser igual ou maior que 3");
        Assert.hasLength(descricao, "A descrição não pode estar vazio");
        Assert.notNull(categoria, "Uma categoria deve ser informada");
        Assert.notNull(usuario, "O usuário deve ser informado");

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataCadastro = LocalDateTime.now();
        this.dono = usuario;

        this.imagens = new HashSet<>();
        this.opinioes = new HashSet<>();
    }

    public boolean isDono(Usuario usuario){
        return this.dono.equals(usuario);
    }

    public void adicionaImagens(Set<String> imagens) {
        this.imagens.addAll(imagens);
    }
}
