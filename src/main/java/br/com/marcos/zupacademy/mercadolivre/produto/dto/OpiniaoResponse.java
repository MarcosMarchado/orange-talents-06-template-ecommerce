package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.OpiniaoDoProduto;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class OpiniaoResponse {

    private Integer nota;
    private String titulo;
    private String descricao;
    private String nomeDoUsuario;

    public OpiniaoResponse(OpiniaoDoProduto opiniaoDoProduto) {
        this.nota = opiniaoDoProduto.getNota();
        this.titulo = opiniaoDoProduto.getTitulo();
        this.descricao = opiniaoDoProduto.getDescricao();
        this.nomeDoUsuario = opiniaoDoProduto.getUsuarioQueOpinou().getUsername();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeDoUsuario() {
        return nomeDoUsuario;
    }

    public static Double calculaMediaDasNotas(List<OpiniaoResponse> opinioes){
        List<Integer> notas = opinioes.stream()
                .map(OpiniaoResponse::getNota)
                .collect(Collectors.toList());
        OptionalDouble average = notas.stream().mapToInt(nota -> nota).average();
        return average.isPresent() ? average.getAsDouble() : 0.0;
    }
}
