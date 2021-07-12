package br.com.marcos.zupacademy.mercadolivre.config.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviaEmailDePerguntaAoDonoDoProduto implements Mailer{

    @Override
    public void send(String corpoDoEmail, String destinatario, String remetente) {
        System.out.println(corpoDoEmail);
        System.out.println(destinatario);
        System.out.println(remetente);
    }
}
