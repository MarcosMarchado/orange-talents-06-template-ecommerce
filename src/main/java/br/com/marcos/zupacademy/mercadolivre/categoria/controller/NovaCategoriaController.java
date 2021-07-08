package br.com.marcos.zupacademy.mercadolivre.categoria.controller;

import br.com.marcos.zupacademy.mercadolivre.categoria.dto.NovaCategoriaRequest;
import br.com.marcos.zupacademy.mercadolivre.categoria.modelo.Categoria;
import br.com.marcos.zupacademy.mercadolivre.categoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class NovaCategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> criarCategoria(@Valid @RequestBody NovaCategoriaRequest novaCategoriaRequest){
        Categoria categoria = novaCategoriaRequest.converter(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();
    }

}
