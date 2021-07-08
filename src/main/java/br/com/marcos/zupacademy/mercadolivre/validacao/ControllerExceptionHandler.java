package br.com.marcos.zupacademy.mercadolivre.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroPadraoResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ErroPadraoResponse> erros = new ArrayList<>();

        exception.getGlobalErrors().forEach(objectError -> {
            ErroPadraoResponse erro = new ErroPadraoResponse(objectError.getObjectName(), objectError.getDefaultMessage());
            erros.add(erro);
        });

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErroPadraoResponse erro = new ErroPadraoResponse(fieldError.getField(), mensagem);
            erros.add(erro);
        });

        return erros;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ObjetoNaoEncontradoResponse objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request){

        ObjetoNaoEncontradoResponse erro = new ObjetoNaoEncontradoResponse(HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                exception.getMessage(),
                request.getRequestURI());

        return erro;
    }

}
