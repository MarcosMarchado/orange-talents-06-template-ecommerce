package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Caracteristicas;

public class CaracteristicasResponse {

    private String nome;

    private String descricao;

    public CaracteristicasResponse(Caracteristicas caracteristicas) {
        this.nome = caracteristicas.getNome();
        this.descricao = caracteristicas.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
