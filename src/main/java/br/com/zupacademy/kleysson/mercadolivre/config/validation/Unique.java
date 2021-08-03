package br.com.zupacademy.kleysson.mercadolivre.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "Valor já cadastrado";

    String field();
    Class<?> entity();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
