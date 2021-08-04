package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.config.validation.Unique;
import br.com.zupacademy.kleysson.mercadolivre.model.Categoria;
import br.com.zupacademy.kleysson.mercadolivre.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaCadastroRequest {

    @NotBlank
    @Unique(field = "nome", entity = Categoria.class)
    private String nome;

    private Long categoriaMae;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaCadastroRequest(String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }


    public Categoria converter(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(nome);

        if(categoriaMaePresente()) {
            Optional<Categoria> categoriaBanco = categoriaRepository.findById(categoriaMae);
            categoria.setCategoria(categoriaBanco.get());
        }

        return categoria;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }

    public boolean categoriaMaePresente() {
        return categoriaMae != null;
    }
}
