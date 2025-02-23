package br.com.marcos.zupacademy.mercadolivre.usuario.modelo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Deprecated
    public Perfil() {
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
