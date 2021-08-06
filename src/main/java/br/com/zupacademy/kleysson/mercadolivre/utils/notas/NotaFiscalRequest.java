package br.com.zupacademy.kleysson.mercadolivre.utils.notas;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idComprador;

    @NotNull
    private Long idCompra;

    public Long getIdComprador() {
        return idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "idComprador=" + idComprador +
                ", idCompra=" + idCompra +
                '}';
    }
}
