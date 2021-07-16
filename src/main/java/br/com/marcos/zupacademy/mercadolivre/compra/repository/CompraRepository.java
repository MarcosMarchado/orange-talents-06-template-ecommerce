package br.com.marcos.zupacademy.mercadolivre.compra.repository;

import br.com.marcos.zupacademy.mercadolivre.compra.modelo.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
