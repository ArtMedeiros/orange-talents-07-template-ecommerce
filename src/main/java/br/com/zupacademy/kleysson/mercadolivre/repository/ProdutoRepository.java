package br.com.zupacademy.kleysson.mercadolivre.repository;

import br.com.zupacademy.kleysson.mercadolivre.model.Categoria;
import br.com.zupacademy.kleysson.mercadolivre.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
