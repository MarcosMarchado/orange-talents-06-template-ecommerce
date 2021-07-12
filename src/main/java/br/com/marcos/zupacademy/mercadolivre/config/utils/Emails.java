package br.com.marcos.zupacademy.mercadolivre.config.utils;

import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(Pergunta pergunta){
        String corpoDoEmail = "Olá, "+ pergunta.getProduto().getEmailDoDono() + " você acabou de receber uma pergunta:" +
                pergunta.getTitulo();
        mailer.send(corpoDoEmail, pergunta.getProduto().getEmailDoDono(), "mercadolivre@zup.com.br");
    }

}
