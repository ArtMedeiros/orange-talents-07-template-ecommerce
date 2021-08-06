package br.com.zupacademy.kleysson.mercadolivre.model;

import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Compra compra;

    @NotNull
    private String pagamentoPlataformaId;

    @NotNull
    private StatusPagamento status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento formaPagamento;

    @NotNull
    private LocalDateTime instantePagamento;

    private Pagamento(){}

    public Pagamento(Compra compra, String pagamentoPlataformaId, StatusPagamento status, GatewayPagamento formaPagamento) {
        Assert.notNull(compra, "A compra deve ser informada");
        Assert.notNull(pagamentoPlataformaId, "O id do pagamento deve ser informado");
        Assert.notNull(status, "O status deve ser informado");
        Assert.notNull(formaPagamento, "O gateway de pagamento deve ser informada");

        this.compra = compra;
        this.pagamentoPlataformaId = pagamentoPlataformaId;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.instantePagamento = LocalDateTime.now();
    }

    public StatusPagamento getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return pagamentoPlataformaId.equals(pagamento.pagamentoPlataformaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagamentoPlataformaId);
    }

    public StatusCompra getStatusNormalizado() {
        return this.status.sincronizarStatus();
    }
}
