package br.com.marcos.zupacademy.mercadolivre.produto.controller;

import br.com.marcos.zupacademy.mercadolivre.produto.UploadFake;
import br.com.marcos.zupacademy.mercadolivre.produto.dto.NovasImagensRequest;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.ProdutoRepository;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class CadastrarImagemParaProduto {

    @Autowired
    private UploadFake uploadFake;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> cadastrarImagemParaProduto(@PathVariable Long id, @Valid NovasImagensRequest imagens){
        /*Pega o usuário logado*/
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com o id "+id+" não encontrado"));

        boolean dono = produto.validaSeUsuarioLogadoEDonoDoProduto(usuarioLogado);

        if(!dono) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        List<String> links = uploadFake.envia(imagens.getImagens());
        produto.associaImagensAoProduto(links);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}
