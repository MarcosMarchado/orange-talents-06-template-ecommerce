package br.com.marcos.zupacademy.mercadolivre.compra.modelo;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario comprador;

    @Enumerated(EnumType.STRING)
    private StatusDaCompra statusDaCompra;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    @Deprecated
    public Compra() {
    }

    public Compra(Integer quantidade,
                  Produto produto,
                  Usuario comprador,
                  FormaDePagamento formaDePagamento) {

        this.quantidade = quantidade;
        this.produto = produto;
        this.valor = produto.getValor();
        this.comprador = comprador;
        this.formaDePagamento = formaDePagamento;
        this.statusDaCompra = StatusDaCompra.INICIADO;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Long getId() {
        return id;
    }
}
