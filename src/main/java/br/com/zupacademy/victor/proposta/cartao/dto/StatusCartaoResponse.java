package br.com.zupacademy.victor.proposta.cartao.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class StatusCartaoResponse {
	
	@NotBlank
	private String resultado;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public StatusCartaoResponse(@NotBlank String resultado) {
		this.resultado = resultado;
	}
	
	public String getResultado() {
		return resultado;
	}
	
}
