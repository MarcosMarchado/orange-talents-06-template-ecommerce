package br.com.marcos.zupacademy.mercadolivre.config.security.dto;

public class TokenResponse {
    private String tipo;
    private String token;

    public TokenResponse(String token) {
        this.tipo = "Bearer";
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }
}
