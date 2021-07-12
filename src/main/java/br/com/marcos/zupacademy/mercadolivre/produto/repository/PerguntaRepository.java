package br.com.marcos.zupacademy.mercadolivre.produto.repository;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    List<Pergunta> findByProdutoId(Long id);
}
