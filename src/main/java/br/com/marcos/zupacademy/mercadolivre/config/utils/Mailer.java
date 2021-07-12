package br.com.marcos.zupacademy.mercadolivre.config.utils;

public interface Mailer {
    void send(String corpoDoEmail, String destinatario, String remetente);
}
