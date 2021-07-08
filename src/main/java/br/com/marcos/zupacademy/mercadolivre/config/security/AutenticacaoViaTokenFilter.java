package br.com.marcos.zupacademy.mercadolivre.config.security;

import br.com.marcos.zupacademy.mercadolivre.usuario.modelo.Usuario;
import br.com.marcos.zupacademy.mercadolivre.usuario.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenConfig tokenConfig;

    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenConfig tokenConfig, UsuarioRepository usuarioRepository) {
        this.tokenConfig = tokenConfig;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = pegarToken(httpServletRequest);
        boolean valido = tokenConfig.isValid(token); /*Faz a checagem se o conteúdo do Token é válido */
        if(valido){
            autenticarCliente(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token) {
        Long usuarioId = tokenConfig.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    /*Pega o token do Header e faz as validações do formato do TOKEN*/
    private String pegarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
