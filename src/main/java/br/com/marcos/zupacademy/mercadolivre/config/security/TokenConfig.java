package br.com.marcos.zupacademy.mercadolivre.config.security;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenConfig {

    @Value("${jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authenticate){
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong("86400000"));

        Usuario usuarioLogado = (Usuario) authenticate.getPrincipal();

        String token = Jwts.builder()
                .setIssuer("MercadoLivre")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public boolean isValid(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token){
        String subject = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(subject);
    }

}
