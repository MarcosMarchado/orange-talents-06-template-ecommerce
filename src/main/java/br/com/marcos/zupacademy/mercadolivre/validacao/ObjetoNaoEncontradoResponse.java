package br.com.marcos.zupacademy.mercadolivre.validacao;

public class ObjetoNaoEncontradoResponse {

    private Integer status;
    private String error;
    private String mensagem;
    private String path;

    public ObjetoNaoEncontradoResponse(Integer status, String error, String mensagem, String path) {
        this.status = status;
        this.error = error;
        this.mensagem = mensagem;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getPath() {
        return path;
    }
}
