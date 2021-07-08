package br.com.marcos.zupacademy.mercadolivre.categoria.repository;

import br.com.marcos.zupacademy.mercadolivre.categoria.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
