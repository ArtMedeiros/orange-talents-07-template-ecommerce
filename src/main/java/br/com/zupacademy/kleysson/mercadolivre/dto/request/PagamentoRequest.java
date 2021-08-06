package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.model.*;

import javax.validation.constraints.NotNull;

public class PagamentoRequest {

    @NotNull
    private String idTransacao;

    @NotNull
    private StatusPagamento status;

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public Pagamento converter(Compra compra, GatewayPagamento formaPagamento) {
        return new Pagamento(compra, idTransacao, status, formaPagamento);
    }
}
