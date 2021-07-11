package br.com.marcos.zupacademy.mercadolivre.produto.controller;

import br.com.marcos.zupacademy.mercadolivre.categoria.repository.CategoriaRepository;
import br.com.marcos.zupacademy.mercadolivre.produto.dto.NovoProdutoRequest;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.ProdutoRepository;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.NaoCadastraProdutoComCaracteristicasIguaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class CadastrarNovoProduto {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @InitBinder
    public void init (WebDataBinder webDataBinder){
        webDataBinder.addValidators(new NaoCadastraProdutoComCaracteristicasIguaisValidator());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarNovoProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest,
                                                  @AuthenticationPrincipal Usuario usuarioLogado){

        Produto produto = novoProdutoRequest.converter(usuarioLogado, categoriaRepository);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

}
