package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import br.com.zupacademy.kleysson.mercadolivre.utils.validation.ObjectExists;
import br.com.zupacademy.kleysson.mercadolivre.model.GatewayPagamento;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DadosCompraRequest {

    @NotNull
    @Positive
    @ObjectExists(field = "id", entity = Produto.class)
    private Long produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento pagamento;

    public Long getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getPagamento() {
        return pagamento;
    }
}
