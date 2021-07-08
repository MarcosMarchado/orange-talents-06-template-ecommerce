package br.com.marcos.zupacademy.mercadolivre.categoria.dto;

import br.com.marcos.zupacademy.mercadolivre.categoria.modelo.Categoria;
import br.com.marcos.zupacademy.mercadolivre.categoria.repository.CategoriaRepository;
import br.com.marcos.zupacademy.mercadolivre.validacao.ObjectNotFoundException;
import br.com.marcos.zupacademy.mercadolivre.validacao.anotacoes.Unico;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank
    @Unico(fieldName = "nome", clazz = Categoria.class, message = "Categoria já existe")
    private String nome;

    private Long idCategoriaMae;

    public NovaCategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {

        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {

        if (idCategoriaMae != null) {
            Optional<Categoria> categoriaMaeOptional = categoriaRepository.findById(idCategoriaMae);
            Categoria categoriaMae = categoriaMaeOptional
                    .orElseThrow(() -> new ObjectNotFoundException("Categoria Mãe com o id "+ idCategoriaMae +" não encontrada."));
            return new Categoria(nome, categoriaMae);
        }
        return new Categoria(nome);
    }
}
