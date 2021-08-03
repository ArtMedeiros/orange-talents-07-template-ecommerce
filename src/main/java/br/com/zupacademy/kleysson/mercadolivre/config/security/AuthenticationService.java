package br.com.zupacademy.kleysson.mercadolivre.config.security;

import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if (usuario.isEmpty())
            throw new UsernameNotFoundException("Dados inválidos!");

        return usuario.get();
    }
}
