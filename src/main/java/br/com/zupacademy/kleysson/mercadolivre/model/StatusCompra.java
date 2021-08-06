package br.com.zupacademy.kleysson.mercadolivre.model;

import br.com.zupacademy.kleysson.mercadolivre.dto.request.PagamentoRequest;

public enum StatusCompra {
    INICIADA,
    SUCESSO,
    FALHA;

    public StatusCompra normalizar(StatusPagamento statusPagamento){
        return (statusPagamento.equals(StatusPagamento.SUCESSO)) ? StatusCompra.SUCESSO : StatusCompra.FALHA;
    }
}
