package br.com.zupacademy.kleysson.mercadolivre.controller;

import br.com.zupacademy.kleysson.mercadolivre.dto.request.AdicionarPerguntaRequest;
import br.com.zupacademy.kleysson.mercadolivre.config.email.Email;
import br.com.zupacademy.kleysson.mercadolivre.model.Pergunta;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.kleysson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {

    private final PerguntaRepository perguntaRepository;
    private final ProdutoRepository produtoRepository;
    private final Email email;

    public PerguntaController(PerguntaRepository perguntaRepository, ProdutoRepository produtoRepository, Email email) {
        this.perguntaRepository = perguntaRepository;
        this.produtoRepository = produtoRepository;
        this.email = email;
    }

    @PostMapping("/produtos/{id}/perguntas")
    public ResponseEntity<?> cadastrar(@PathVariable Long id, @RequestBody @Valid AdicionarPerguntaRequest perguntaRequest, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);

        if(produtoObj.isEmpty())
            return ResponseEntity.badRequest().build();

        if(produtoObj.get().getDono().equals(usuario))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Pergunta pergunta = perguntaRequest.converter(usuario, produtoObj.get());
        perguntaRepository.save(pergunta);

        email.novaPergunta(pergunta);

        return ResponseEntity.ok().build();
    }
}
