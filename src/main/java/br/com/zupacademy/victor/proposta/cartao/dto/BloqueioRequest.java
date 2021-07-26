package br.com.zupacademy.victor.proposta.cartao.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class BloqueioRequest {
	
	@NotBlank
	private String sistemaResponsavel;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public BloqueioRequest(@NotBlank String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
