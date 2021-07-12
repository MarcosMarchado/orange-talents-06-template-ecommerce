package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Imagem;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Pergunta;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhesDoProdutoResponse {

    private String nome;
    private Set<String> fotos;
    private Integer quantidade;
    private String descricao;
    private BigDecimal valor;
    private Set<CaracteristicasResponse> caracteristicas;
    private List<String> perguntas;
    private String vendidoPor;
    private List<OpiniaoResponse> opinioes;
    private Double mediaDasOpinioes;
    private Integer numeroTotalDeNotas;

    public DetalhesDoProdutoResponse(Produto produto) {
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.vendidoPor = produto.getEmailDoDono();
        this.nome = produto.getNome();
        this.valor = produto.getValor();

        this.opinioes = produto.getOpinioes()
                .stream()
                .map(opiniaoDoProduto -> new OpiniaoResponse(opiniaoDoProduto))
                .collect(Collectors.toList());
        this.mediaDasOpinioes = OpiniaoResponse.calculaMediaDasNotas(opinioes);
        this.numeroTotalDeNotas = opinioes.size();

        this.fotos = produto.getImagens()
                .stream()
                .map(Imagem::getUrlImagem)
                .collect(Collectors.toSet());

        this.caracteristicas = produto.getCaracteristicas()
                .stream()
                .map(CaracteristicasResponse::new)
                .collect(Collectors.toSet());

        this.perguntas = produto.getPerguntas()
                .stream()
                .map(Pergunta::getTitulo)
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getFotos() {
        return fotos;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicasResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public String getVendidoPor() {
        return vendidoPor;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public Double getMediaDasOpinioes() {
        return mediaDasOpinioes;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getNumeroTotalDeNotas() {
        return numeroTotalDeNotas;
    }
}
