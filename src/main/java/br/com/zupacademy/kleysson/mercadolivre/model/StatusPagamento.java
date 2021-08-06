package br.com.zupacademy.kleysson.mercadolivre.model;

public enum StatusPagamento {

    ERRO {
        @Override
        public StatusCompra sincronizarStatus() {
            return StatusCompra.FALHA;
        }
    },
    SUCESSO {
        @Override
        public StatusCompra sincronizarStatus() {
            return StatusCompra.SUCESSO;
        }
    };

    public abstract StatusCompra sincronizarStatus();
}
