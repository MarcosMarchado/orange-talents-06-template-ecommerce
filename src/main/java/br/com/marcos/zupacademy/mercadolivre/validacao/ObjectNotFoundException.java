package br.com.marcos.zupacademy.mercadolivre.validacao;

public class ObjectNotFoundException extends RuntimeException{

    private String msg;

    public ObjectNotFoundException(String msg) {
        super(msg);

    }
}
