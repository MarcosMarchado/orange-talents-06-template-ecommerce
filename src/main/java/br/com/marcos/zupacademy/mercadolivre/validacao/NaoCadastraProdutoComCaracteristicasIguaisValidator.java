package br.com.marcos.zupacademy.mercadolivre.validacao;

import br.com.marcos.zupacademy.mercadolivre.produto.dto.NovoProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NaoCadastraProdutoComCaracteristicasIguaisValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovoProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        NovoProdutoRequest novoProdutoRequest = (NovoProdutoRequest) object;
        if(novoProdutoRequest.naoCriaCaracteristicasComNomeRepetido()){
            errors.rejectValue("caracteristicas", null, "Não pode ter caracteristicas com nomes iguais.");
        }

    }
}
