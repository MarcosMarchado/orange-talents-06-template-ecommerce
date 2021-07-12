package br.com.marcos.zupacademy.mercadolivre.produto.modelo;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuarioLogado;

    public Pergunta(String titulo, Produto produto, Usuario usuarioLogado) {

        this.titulo = titulo;
        this.produto = produto;
        this.usuarioLogado = usuarioLogado;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

}
