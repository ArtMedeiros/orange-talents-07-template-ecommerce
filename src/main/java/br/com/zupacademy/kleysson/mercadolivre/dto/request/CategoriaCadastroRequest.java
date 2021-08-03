package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.config.validation.Unique;
import br.com.zupacademy.kleysson.mercadolivre.model.Categoria;
import br.com.zupacademy.kleysson.mercadolivre.repository.CategoriaRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaCadastroRequest {

    @NotBlank
    @Unique(field = "nome", entity = Categoria.class)
    private String nome;

    private Long categoriaMae;

    public Categoria converter(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(nome);

        if(categoriaMaePresente()) {
            Optional<Categoria> categoriaBanco = categoriaRepository.findById(categoriaMae);
            categoria.setCategoria(categoriaBanco.get());
        }

        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }

    public boolean categoriaMaePresente() {
        return categoriaMae != null;
    }
}
