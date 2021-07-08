package br.com.marcos.zupacademy.mercadolivre.validacao.anotacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UnicoValidator.class)
public @interface Unico {
    String message() default "O campo já existe";
    String fieldName();
    Class<?> clazz();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

