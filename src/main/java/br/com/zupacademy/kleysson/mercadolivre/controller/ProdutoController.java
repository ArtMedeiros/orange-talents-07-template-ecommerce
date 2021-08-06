package br.com.zupacademy.kleysson.mercadolivre.controller;

import br.com.zupacademy.kleysson.mercadolivre.utils.files.Uploader;
import br.com.zupacademy.kleysson.mercadolivre.dto.request.AdicionarImagemRequest;
import br.com.zupacademy.kleysson.mercadolivre.dto.request.ProdutoCadastroRequest;
import br.com.zupacademy.kleysson.mercadolivre.dto.response.DetalhesProdutoResponse;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.kleysson.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.kleysson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final PerguntaRepository perguntaRepository;
    private final CategoriaRepository categoriaRepository;
    private final Uploader uploaderFile;

    public ProdutoController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, CategoriaRepository categoriaRepository, Uploader uploaderFile) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.categoriaRepository = categoriaRepository;
        this.uploaderFile = uploaderFile;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoCadastroRequest produtoCadastro, @AuthenticationPrincipal Usuario usuario) {
        produtoRepository.save(produtoCadastro.converter(categoriaRepository, usuario));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable Long id, @Valid AdicionarImagemRequest imagens, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);

        //Verifica se o produto está cadastrado
        if(produtoObj.isEmpty())
            return ResponseEntity.badRequest().build();

        //Verifica se o usuário é o dono do produto
        if(!produtoObj.get().isDono(usuario))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        //Faz o upload das imagens
        Set<String> listaLinks = uploaderFile.enviar(imagens.getImagens());

        //Salva as imagens no produto
        produtoObj.get().adicionaImagens(listaLinks);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProdutoResponse> detalhesProduto(@PathVariable Long id) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);

        //Verifica se o produto está cadastrado
        if(produtoObj.isEmpty())
            return ResponseEntity.notFound().build();

        DetalhesProdutoResponse detalhesProduto = new DetalhesProdutoResponse(produtoObj.get());

        return ResponseEntity.ok().body(detalhesProduto);
    }
}
