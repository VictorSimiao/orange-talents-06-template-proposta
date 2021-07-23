package br.com.zupacademy.victor.proposta.biometria.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Validator implements ConstraintValidator<Base64, String> {
	private final Logger logger = LoggerFactory.getLogger(Base64Validator.class);

	@Override
	public boolean isValid(String valor, ConstraintValidatorContext context) {
		if (valor == null) {
			return true;
		}
		try {
			java.util.Base64.getDecoder().decode(valor.getBytes());
			logger.info("Valor em formato Base64");
			return true;
		} catch (IllegalArgumentException e) {
			logger.error("Valor não está em Base64");
			return false;
		}

	}

}
