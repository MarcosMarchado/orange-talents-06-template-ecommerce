package br.com.marcos.zupacademy.mercadolivre.usuario.controller;

import br.com.marcos.zupacademy.mercadolivre.usuario.dto.UsuarioRequest;
import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class CadastroNovoUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UsuarioRequest novoUsuarioRequest){
        Usuario usuario = novoUsuarioRequest.converter();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

}
