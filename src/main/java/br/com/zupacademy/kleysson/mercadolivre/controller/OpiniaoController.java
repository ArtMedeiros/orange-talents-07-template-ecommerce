package br.com.zupacademy.kleysson.mercadolivre.controller;

import br.com.zupacademy.kleysson.mercadolivre.dto.request.AdicionarOpiniaoRequest;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.kleysson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;
    private final ProdutoRepository produtoRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/produtos/{id}/opinioes")
    public ResponseEntity<?> cadastrar(@PathVariable Long id, @RequestBody @Valid AdicionarOpiniaoRequest opiniao, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);

        if(produtoObj.isEmpty())
            return ResponseEntity.badRequest().build();

        opiniaoRepository.save(opiniao.converter(produtoObj.get(), usuario));

        return ResponseEntity.ok().build();
    }
}
