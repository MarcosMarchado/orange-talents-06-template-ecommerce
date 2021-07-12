package br.com.marcos.zupacademy.mercadolivre.produto.dto;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Pergunta;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Produto;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;
    /*Apenas o getter, pois construtor com 1 atributo da erro*/
    public String getTitulo() {
        return titulo;
    }

    public Pergunta converter(Produto produto, Usuario usuarioLogado){
        return new Pergunta(this.titulo, produto, usuarioLogado);
    }

}
