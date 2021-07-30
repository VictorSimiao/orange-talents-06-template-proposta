package br.com.zupacademy.victor.proposta.criptografia;

import javax.persistence.AttributeConverter;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class ConversorCriptografia implements AttributeConverter<String, String>{
	
	TextEncryptor textEncryptor = Encryptors.delux("${proposta.criptografia.secret}", "5c0744940b5c369b");
	
	@Override
	public String convertToDatabaseColumn(String campo) {
		return textEncryptor.encrypt(campo);
	}

	@Override
	public String convertToEntityAttribute(String campo) {
		return textEncryptor.decrypt(campo);
	}

}
