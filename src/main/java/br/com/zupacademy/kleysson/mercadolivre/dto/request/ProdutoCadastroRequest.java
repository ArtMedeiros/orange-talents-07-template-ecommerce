package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.config.validation.ObjectExists;
import br.com.zupacademy.kleysson.mercadolivre.model.Caracteristica;
import br.com.zupacademy.kleysson.mercadolivre.model.Categoria;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Optional;
import java.util.Set;

public class ProdutoCadastroRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @Size(min = 3)
    private Set<Caracteristica> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ObjectExists(field = "id", entity = Categoria.class)
    private Long categoria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutoCadastroRequest(String nome, Double valor, Integer quantidade, Set<Caracteristica> caracteristicas, String descricao, Long categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto converter(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoriaObj = categoriaRepository.findById(categoria);

        return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoriaObj.get(), usuario);
    }
}
