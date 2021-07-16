package br.com.marcos.zupacademy.mercadolivre.compra.dto;

import br.com.marcos.zupacademy.mercadolivre.compra.modelo.Compra;
import br.com.marcos.zupacademy.mercadolivre.compra.modelo.FormaDePagamento;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private FormaDePagamento formaDePagamento;

    public NovaCompraRequest(@NotNull @Positive Integer quantidade,
                             @NotNull Long idProduto,
                             @NotNull FormaDePagamento formaDePagamento) {

        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.formaDePagamento = formaDePagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public Compra converter(Usuario comprador, Produto produto) {
        return new Compra(this.quantidade, produto, comprador, this.formaDePagamento);
    }


}
