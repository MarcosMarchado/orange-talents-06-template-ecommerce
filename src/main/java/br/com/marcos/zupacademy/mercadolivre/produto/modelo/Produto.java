package br.com.marcos.zupacademy.mercadolivre.produto.modelo;

import br.com.marcos.zupacademy.mercadolivre.categoria.modelo.Categoria;
import br.com.marcos.zupacademy.mercadolivre.produto.dto.CaracteristicasRequest;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime cadastradoEm = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristicas> caracteristicas = new HashSet<>();

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Imagem> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome,
                   BigDecimal valor,
                   Integer quantidade,
                   String descricao,
                   List<CaracteristicasRequest> caracteristicasRequest,
                   Categoria categoria, Usuario usuario) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

        this.caracteristicas = caracteristicasRequest.stream()
                .map(caracteristica -> caracteristica.converter(this)).collect(Collectors.toSet());

    }

    public String getEmailDoDono(){
        return this.usuario.getUsername();
    }

    public void associaImagensAoProduto(List<String> urlImages){
        Set<Imagem> imagens = urlImages.stream().map(imagem -> new Imagem(imagem, this))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean validaSeUsuarioLogadoEDonoDoProduto(Usuario usuarioLogado) {
        return this.usuario.equals(usuarioLogado);
    }
}
