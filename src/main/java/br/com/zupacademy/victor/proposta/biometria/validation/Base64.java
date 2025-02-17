package br.com.zupacademy.victor.proposta.biometria.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {Base64Validator.class})
@Target(ElementType.FIELD) 
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64 {
	String message() default "Informe um valor em Base64";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
