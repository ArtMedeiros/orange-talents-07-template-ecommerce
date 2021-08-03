package br.com.zupacademy.kleysson.mercadolivre.config.validation;

import br.com.zupacademy.kleysson.mercadolivre.dto.request.CategoriaCadastroRequest;
import br.com.zupacademy.kleysson.mercadolivre.model.Categoria;
import br.com.zupacademy.kleysson.mercadolivre.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CategoriaExisteValidator implements Validator {

    private final CategoriaRepository categoriaRepository;

    public CategoriaExisteValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaCadastroRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CategoriaCadastroRequest request = (CategoriaCadastroRequest) o;

        if(request.categoriaMaePresente()) {
            Optional<Categoria> categoria = categoriaRepository.findById(request.getCategoriaMae());

            if(categoria.isEmpty())
                errors.rejectValue("categoriaMae", null,"Categoria mãe não existe");
        }
    }
}
