package br.com.zupacademy.kleysson.mercadolivre.repository;

import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
