package br.com.zupacademy.kleysson.mercadolivre.config.validation;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    private String field;
    private Class<?> entity;

    private EntityManager em;

    public UniqueValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String jpql = "SELECT c FROM " +entity.getName()+ " c WHERE " +field+ "= :pValue";
        Query query = em.createQuery(jpql);
        query.setParameter("pValue", value);
        List<?> resultList = query.getResultList();

        return resultList.isEmpty();
    }
}
