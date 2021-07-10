package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.categoria.modelo.Categoria;
import br.com.marcos.zupacademy.mercadolivre.categoria.repository.CategoriaRepository;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @NotNull
    @Length(max = 1000)
    private String descricao;

    @NotNull
    private Long idCategoria;

    @Valid
    @Size(min = 3)
    private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();

    public NovoProdutoRequest(@NotBlank String nome,
                              @Positive BigDecimal valor,
                              @NotNull @Min(0) Integer quantidade,
                              @NotNull @Length(max = 1000) String descricao,
                              @NotNull Long idCategoria,
                              @Valid @Size(min = 3) List<CaracteristicasRequest> caracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    /*Getter para caracteristicas pois não estava sendo capturado os errros de validação*/
    public List<CaracteristicasRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto converter(Usuario usuario, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria Mãe com o id "+ idCategoria +" não encontrada."));

        return new Produto(this.nome, this.valor, this.quantidade, this.descricao, caracteristicas, categoria, usuario);
    }

    public boolean naoCriaCaracteristicasComNomeRepetido(){
        Set<String> nomesRepetidos = new HashSet<>();
        for (CaracteristicasRequest caracteristica : this.caracteristicas) {
            if(!nomesRepetidos.add(caracteristica.getNome())){
                return true;
            }
        }
        return false;
    }
}
