package br.com.marcos.zupacademy.mercadolivre.produto.repository;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
