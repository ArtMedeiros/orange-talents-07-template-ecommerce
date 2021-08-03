package br.com.zupacademy.kleysson.mercadolivre.config.security;

import br.com.zupacademy.kleysson.mercadolivre.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenManager tokenManager;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationService authenticationService;

    public SecurityConfiguration(TokenManager tokenManager, UsuarioRepository usuarioRepository, AuthenticationService authenticationService) {
        this.tokenManager = tokenManager;
        this.usuarioRepository = usuarioRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/produto/{id:[0-9]+}").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new JwtAuthenticationFilter(tokenManager, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }
}
