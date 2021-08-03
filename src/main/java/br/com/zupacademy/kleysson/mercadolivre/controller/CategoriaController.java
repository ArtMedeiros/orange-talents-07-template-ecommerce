package br.com.zupacademy.kleysson.mercadolivre.controller;

import br.com.zupacademy.kleysson.mercadolivre.config.validation.CategoriaExisteValidator;
import br.com.zupacademy.kleysson.mercadolivre.dto.request.CategoriaCadastroRequest;
import br.com.zupacademy.kleysson.mercadolivre.model.Categoria;
import br.com.zupacademy.kleysson.mercadolivre.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaExisteValidator categoriaExisteValidator;

    public CategoriaController(CategoriaRepository categoriaRepository, CategoriaExisteValidator categoriaExisteValidator) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaExisteValidator = categoriaExisteValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(categoriaExisteValidator);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaCadastroRequest categoriaCadastro) {
        categoriaRepository.save(categoriaCadastro.converter(categoriaRepository));

        return ResponseEntity.ok().build();
    }
}
