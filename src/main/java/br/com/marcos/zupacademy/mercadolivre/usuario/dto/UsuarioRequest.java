package br.com.marcos.zupacademy.mercadolivre.usuario.dto;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.anotacoes.Unico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @Email
    @NotBlank
    @Unico(fieldName = "email", clazz = Usuario.class, message = "E-mail já está sendo usado")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;


    public UsuarioRequest(
            @Email @NotBlank String email,
            @NotBlank @Length(min = 6) String senha) {

        this.email = email;
        this.senha = senha;
    }

    public Usuario converter(){
        return new Usuario(email, senha);
    }

}
