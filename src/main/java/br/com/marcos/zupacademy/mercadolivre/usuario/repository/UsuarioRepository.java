package br.com.marcos.zupacademy.mercadolivre.usuario.repository;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
