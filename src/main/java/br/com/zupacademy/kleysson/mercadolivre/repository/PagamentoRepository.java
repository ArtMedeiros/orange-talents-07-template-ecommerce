package br.com.zupacademy.kleysson.mercadolivre.repository;

import br.com.zupacademy.kleysson.mercadolivre.model.Pagamento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {

    List<Pagamento> findByCompraId(Long id);
}
