package br.com.marcos.zupacademy.mercadolivre.produto.controller;

import br.com.marcos.zupacademy.mercadolivre.produto.dto.NovaOpiniaoRequest;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.OpiniaoDoProduto;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.OpiniaoRepository;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.ProdutoRepository;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class CadastrarUmaNovaOpniaoAoProduto {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping("/{id}/opiniao")
    public ResponseEntity<?> cadastrarUmOpiniaoAoProduto(@PathVariable Long id,
                                                         @AuthenticationPrincipal Usuario usuarioLogado,
                                                         @Valid @RequestBody NovaOpiniaoRequest novaOpiniaoRequest){

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com o id "+id+" não encontrado"));

        /*Dono não pode dar opinião sobre os seus produtos*/
        boolean dono = produto.validaSeUsuarioLogadoEDonoDoProduto(usuarioLogado);
        if(dono) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        OpiniaoDoProduto opiniaoDoProduto = novaOpiniaoRequest.converter(usuarioLogado, produto);

        opiniaoRepository.save(opiniaoDoProduto);

        return ResponseEntity.ok().build();
    }

}
