package br.com.marcos.zupacademy.mercadolivre.validacao;

public class ErroPadraoResponse {
    private String campo;

    private String mensagem;

    public ErroPadraoResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
