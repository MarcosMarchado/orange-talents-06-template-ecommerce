package br.com.marcos.zupacademy.mercadolivre.validacao.anotacoes;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoValidator implements ConstraintValidator<Unico, Object>{

    @Autowired
    private EntityManager entityManager;
    private Class<?> clazz;
    private String fieldName;

    @Override
    public void initialize(Unico constraintAnnotation) {
        this.clazz = constraintAnnotation.clazz();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String JPQL = "SELECT c FROM "
                + clazz.getName() + " c WHERE c."
                +fieldName+" = :pValue";

        Query query = entityManager.createQuery(JPQL).setParameter("pValue", value);
        return query.getResultList().isEmpty();
    }
}
