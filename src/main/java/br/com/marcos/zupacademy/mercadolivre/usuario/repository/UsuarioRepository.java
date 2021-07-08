package br.com.marcos.zupacademy.mercadolivre.usuario.repository;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
