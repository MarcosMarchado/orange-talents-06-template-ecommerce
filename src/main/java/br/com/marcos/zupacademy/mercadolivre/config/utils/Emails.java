package br.com.marcos.zupacademy.mercadolivre.config.utils;

import br.com.marcos.zupacademy.mercadolivre.compra.modelo.Compra;
import br.com.marcos.zupacademy.mercadolivre.produto.modelo.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;

    private String remetente = "mercadolivre@zup.com.br";

    public void novaPergunta(Pergunta pergunta){
        String corpoDoEmail = "Olá, "+ pergunta.getProduto().getEmailDoDono() + " você acabou de receber uma pergunta:" +
                pergunta.getTitulo();
        mailer.send(corpoDoEmail, pergunta.getProduto().getEmailDoDono(), remetente);
    }

    public void novaCompra(Compra compra){
        String corpoDoEmail = "Olá "+ compra.getProduto().getEmailDoDono()
                + " o usuário: "
                +  compra.getComprador().getUsername()
                + " deseja fazer a compra do Produto "
                + compra.getProduto().getNome();

        mailer.send(corpoDoEmail, compra.getProduto().getEmailDoDono(), remetente);
    }

}
