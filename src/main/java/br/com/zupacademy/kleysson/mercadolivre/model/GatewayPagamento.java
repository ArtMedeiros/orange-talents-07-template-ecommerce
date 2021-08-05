package br.com.zupacademy.kleysson.mercadolivre.model;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
    PAYPAL {
        @Override
        public String redirectUrl(Long id, UriComponentsBuilder uriBuilder) {
            String urlRetorno = uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(id).toString();
            return "paypal.com?buyerId="+id
                    +"&redirectUrl="+urlRetorno;
        }
    },
    PAGSEGURO {
        @Override
        public String redirectUrl(Long id, UriComponentsBuilder uriBuilder) {
            String urlRetorno = uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(id).toString();
            return "pagseguro.com?returnId="+id
                    +"&redirectUrl="+urlRetorno;
        }
    };

    public abstract String redirectUrl(Long id, UriComponentsBuilder uriBuilder);
}
