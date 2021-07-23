package br.com.zupacademy.victor.proposta.biometria.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.victor.proposta.biometria.model.Biometria;
import br.com.zupacademy.victor.proposta.biometria.validation.Base64;
import br.com.zupacademy.victor.proposta.cartao.model.Cartao;

public class BiometriaRequest {
	
	@NotBlank
	@Base64
	private String fingerPrint;

	@JsonCreator(mode = Mode.PROPERTIES)
	public BiometriaRequest(@NotBlank String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}
	
	public Biometria toModel(@NotNull Cartao cartao) {
		Assert.notNull(cartao, "É necessário ter um cartão");
		return new Biometria(fingerPrint,cartao);
	}
	
	
}
