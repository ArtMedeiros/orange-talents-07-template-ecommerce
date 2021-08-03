package br.com.zupacademy.kleysson.mercadolivre.config.security;

import br.com.zupacademy.kleysson.mercadolivre.model.Usuario;
import br.com.zupacademy.kleysson.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(TokenManager tokenManager, UsuarioRepository usuarioRepository) {
        this.tokenManager = tokenManager;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = recuperarToken(request);

        if(token.isPresent() && tokenManager.isValid(token.get())) {
            autenticarCliente(token.get());
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenManager.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Optional<String> recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        return Optional.ofNullable(token);
    }
}
