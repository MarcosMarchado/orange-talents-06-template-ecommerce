package br.com.marcos.zupacademy.mercadolivre.config.security.controller;

import br.com.marcos.zupacademy.mercadolivre.config.security.TokenConfig;
import br.com.marcos.zupacademy.mercadolivre.config.security.dto.TokenResponse;
import br.com.marcos.zupacademy.mercadolivre.config.security.dto.UsuarioLogin;
import br.com.marcos.zupacademy.mercadolivre.usuario.dto.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenConfig tokenConfig;

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@Valid @RequestBody UsuarioLogin usuarioLogin){

        Authentication authenticate = authenticationManager.authenticate(usuarioLogin.gerarOAuthentication());
        String token = tokenConfig.gerarToken(authenticate);
        return ResponseEntity.ok(new TokenResponse(token));
    }

}
