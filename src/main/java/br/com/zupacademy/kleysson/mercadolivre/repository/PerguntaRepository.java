package br.com.zupacademy.kleysson.mercadolivre.repository;

import br.com.zupacademy.kleysson.mercadolivre.model.Pergunta;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PerguntaRepository extends CrudRepository<Pergunta, Long> {

    Set<Pergunta> findByProdutoId(Long id);
}
