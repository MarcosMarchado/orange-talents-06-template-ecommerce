package br.com.marcos.zupacademy.mercadolivre.produto.modelo;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;

import javax.persistence.*;

@Entity
public class OpiniaoDoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Usuario usuarioQueOpinou;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public OpiniaoDoProduto() {
    }

    public OpiniaoDoProduto(Integer nota,
                            String titulo,
                            String descricao,
                            Usuario usuarioQueOpinou,
                            Produto produto) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioQueOpinou = usuarioQueOpinou;
        this.produto = produto;
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

    public Usuario getUsuarioQueOpinou() {
        return usuarioQueOpinou;
    }
}
