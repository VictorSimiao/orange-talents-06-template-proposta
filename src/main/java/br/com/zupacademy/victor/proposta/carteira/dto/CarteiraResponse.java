package br.com.zupacademy.victor.proposta.carteira.dto;

import javax.validation.constraints.NotBlank;

public class CarteiraResponse {
	
	@NotBlank
	private String resultado;
	@NotBlank
	private String id;
	
	public CarteiraResponse(@NotBlank String resultado, @NotBlank String id) {
		this.resultado = resultado;
		this.id = id;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public String getId() {
		return id;
	}
	
}
