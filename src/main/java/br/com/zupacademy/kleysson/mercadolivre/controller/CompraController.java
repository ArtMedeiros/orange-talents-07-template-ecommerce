package br.com.zupacademy.kleysson.mercadolivre.controller;

import br.com.zupacademy.kleysson.mercadolivre.config.email.Email;
import br.com.zupacademy.kleysson.mercadolivre.dto.ErrorFormatDTO;
import br.com.zupacademy.kleysson.mercadolivre.dto.request.DadosCompraRequest;
import br.com.zupacademy.kleysson.mercadolivre.model.Compra;
import br.com.zupacademy.kleysson.mercadolivre.model.GatewayPagamento;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.kleysson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;
    private final Email email;

    public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository, Email email) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.email = email;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid DadosCompraRequest compraRequest, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder){
        Produto produto = produtoRepository.findById(compraRequest.getProduto()).get();
        int quantidade = compraRequest.getQuantidade();
        boolean verificaEstoque = produto.abaterEstoque(compraRequest.getQuantidade());

        if(verificaEstoque) {
            GatewayPagamento pagamento = compraRequest.getPagamento();

            Compra compra = new Compra(produto, quantidade, usuario, pagamento);
            compraRepository.save(compra);

            email.novaCompra(compra);

            return ResponseEntity.ok(pagamento.redirectUrl(compra.getId(), uriBuilder));
        }

        return ResponseEntity.badRequest().body(new ErrorFormatDTO("quantidade", "Não há estoque suficiente do produto"));
    }
}
