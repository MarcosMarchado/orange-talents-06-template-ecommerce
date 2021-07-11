package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.OpiniaoDoProduto;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class NovaOpiniaoRequest {

    @NotNull
    @Range(min = 1, max = 5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(
            @NotBlank String titulo,
            @NotNull @Range(min = 1, max = 5) Integer nota,
            @NotBlank @Length(max = 500) String descricao) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoDoProduto converter(Usuario usuarioLogado, Produto produto) {
        return new OpiniaoDoProduto(this.nota, this.titulo, this.descricao, usuarioLogado, produto);
    }
}
