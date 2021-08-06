package br.com.zupacademy.kleysson.mercadolivre.utils.notas;

import br.com.zupacademy.kleysson.mercadolivre.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotasFiscais {

    public void enviar(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idComprador", compra.getComprador().getId(), "idCompra", compra.getId());

        restTemplate.postForObject("http://localhost:8080/outros-servicos/notas-fiscais", request, String.class);
    }
}
