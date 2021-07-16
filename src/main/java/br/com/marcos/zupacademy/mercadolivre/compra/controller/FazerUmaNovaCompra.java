package br.com.marcos.zupacademy.mercadolivre.compra.controller;

import br.com.marcos.zupacademy.mercadolivre.compra.dto.NovaCompraRequest;
import br.com.marcos.zupacademy.mercadolivre.compra.modelo.Compra;
import br.com.marcos.zupacademy.mercadolivre.compra.repository.CompraRepository;
import br.com.marcos.zupacademy.mercadolivre.config.utils.Emails;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.produto.repository.ProdutoRepository;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class FazerUmaNovaCompra {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Emails emails;

    @PostMapping("/compra")
    public ResponseEntity<?> novaCompra(@AuthenticationPrincipal Usuario comprador,
                                        @Valid @RequestBody NovaCompraRequest novaCompraRequest){

        Long idDoProduto = novaCompraRequest.getIdProduto();
        Produto produto = produtoRepository.findById(idDoProduto)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com o id " + idDoProduto + " não encontrado"));

        int quantidade = novaCompraRequest.getQuantidade();
        boolean abateuDoEstoque = produto.abateDoEstoque(quantidade);

        if(abateuDoEstoque){
            Compra compra = novaCompraRequest.converter(comprador, produto);
            compraRepository.save(compra);
            emails.novaCompra(compra);
            String linkRedirecionamentoGateway = novaCompraRequest
                    .getFormaDePagamento()
                    .getGatewayDePagamento()
                    .redirecionamento(compra.getId(), idDoProduto);
            return ResponseEntity.status(HttpStatus.FOUND).body(linkRedirecionamentoGateway);
        }

        return ResponseEntity.badRequest().build();

    }

}
