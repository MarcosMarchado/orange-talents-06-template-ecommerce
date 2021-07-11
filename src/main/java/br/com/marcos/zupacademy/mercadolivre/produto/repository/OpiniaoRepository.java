package br.com.marcos.zupacademy.mercadolivre.produto.repository;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.OpiniaoDoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpiniaoRepository extends JpaRepository<OpiniaoDoProduto, Long> {
}
