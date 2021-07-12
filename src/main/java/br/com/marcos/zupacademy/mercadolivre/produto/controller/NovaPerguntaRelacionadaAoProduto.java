package br.com.marcos.zupacademy.mercadolivre.produto.controller;

import br.com.marcos.zupacademy.mercadolivre.config.utils.Emails;
import br.com.marcos.zupacademy.mercadolivre.produto.dto.NovaPerguntaRequest;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Pergunta;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.PerguntaRepository;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.ProdutoRepository;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class NovaPerguntaRelacionadaAoProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private Emails emails;

    @PostMapping("/{id}/perguntas")
    public ResponseEntity<?> novaPergunta(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long id,
            @Valid @RequestBody NovaPerguntaRequest novaPerguntaRequest){

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com o id "+id+" não encontrado"));

        Pergunta pergunta = novaPerguntaRequest.converter(produto, usuario);
        emails.novaPergunta(pergunta);
        perguntaRepository.save(pergunta);

        return ResponseEntity.ok().build();
    }

}
