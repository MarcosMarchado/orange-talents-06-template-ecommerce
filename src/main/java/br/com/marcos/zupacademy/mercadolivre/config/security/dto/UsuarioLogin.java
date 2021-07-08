package br.com.marcos.zupacademy.mercadolivre.config.security.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioLogin {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;


    public UsuarioLogin(
            @Email @NotBlank String email,
            @NotBlank @Length(min = 6) String senha) {

        this.email = email;
        this.senha = senha;
    }

    public Authentication gerarOAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
