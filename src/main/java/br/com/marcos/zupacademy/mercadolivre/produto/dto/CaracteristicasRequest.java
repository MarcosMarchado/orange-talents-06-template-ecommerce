package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Caracteristicas;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    /*Getters criados para a validação*/
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicasRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristicas converter(Produto produto){
        return new Caracteristicas(this.nome, this.descricao, produto);
    }

}
