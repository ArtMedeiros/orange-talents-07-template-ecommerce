package br.com.zupacademy.kleysson.mercadolivre.controller;

import br.com.zupacademy.kleysson.mercadolivre.utils.notas.NotaFiscalRequest;
import br.com.zupacademy.kleysson.mercadolivre.utils.ranking.RankingVendedorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/outros-servicos")
public class OutrosServicosController {

    @PostMapping("/notas-fiscais")
    public ResponseEntity<?> criarNota(@RequestBody @Valid NotaFiscalRequest notaRequest) {
        System.out.println("Nota fiscal criada: "+notaRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ranking")
    public ResponseEntity<?> ranking(@RequestBody @Valid RankingVendedorRequest rankingRequest) {
        System.out.println("Ranking de vendedores atualizado: "+rankingRequest);

        return ResponseEntity.ok().build();
    }
}
