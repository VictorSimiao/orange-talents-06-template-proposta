package br.com.zupacademy.victor.proposta.viagem.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class AvisoResponse {
	
	@NotBlank
	private String resultado;

	@JsonCreator(mode = Mode.PROPERTIES)
	public AvisoResponse(@NotBlank String resultado) {
		this.resultado = resultado;
	}
	
	public String getResultado() {
		return resultado;
	}
}
