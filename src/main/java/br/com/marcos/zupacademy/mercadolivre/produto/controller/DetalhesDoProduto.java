package br.com.marcos.zupacademy.mercadolivre.produto.controller;

import br.com.marcos.zupacademy.mercadolivre.produto.dto.DetalhesDoProdutoResponse;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.ProdutoRepository;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class DetalhesDoProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<?> detalharProduto(@PathVariable Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com o id " + id + " não encontrado"));

        return ResponseEntity.ok(new DetalhesDoProdutoResponse(produto));
    }

}
