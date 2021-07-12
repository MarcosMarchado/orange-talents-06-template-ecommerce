package br.com.marcos.zupacademy.mercadolivre.produto.modelo;

import javax.persistence.*;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlImagem;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Imagem() {
    }

    public Imagem(String urlImagem, Produto produto) {
        this.urlImagem = urlImagem;
        this.produto = produto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
}
