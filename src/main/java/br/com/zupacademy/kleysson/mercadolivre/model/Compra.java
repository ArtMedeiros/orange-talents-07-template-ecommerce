package br.com.zupacademy.kleysson.mercadolivre.model;

import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.sql.RowSet;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @Positive
    private Integer quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @Enumerated(value = EnumType.STRING)
    private StatusCompra status;

    @Enumerated(value = EnumType.STRING)
    private GatewayPagamento pagamento;

    @NotNull
    @Positive
    private Double valorCompra;

    private Compra() {}

    public Compra(Produto produto, int quantidade, Usuario comprador, GatewayPagamento pagamento) {
        Assert.notNull(produto, "Produto não informado");
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que 0");
        Assert.notNull(comprador, "O comprador deve ser informado");
        Assert.notNull(pagamento, "O tipo de pagamento deve ser informado");

        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.status = StatusCompra.INICIADA;
        this.valorCompra = produto.getValor();
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Usuario getDonoProduto() {
        return this.produto.getDono();
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public Usuario getComprador() {
        return this.comprador;
    }

    public Long getId() {
        return this.id;
    }
}
