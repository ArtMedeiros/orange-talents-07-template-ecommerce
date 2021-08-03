package br.com.zupacademy.kleysson.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    @NotBlank
    @Size(min = 6)
    private String password;

    public SenhaLimpa(@NotBlank @Size(min = 6) String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
