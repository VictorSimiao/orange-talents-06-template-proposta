package br.com.zupacademy.victor.proposta.proposta.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.model.StatusProposta;

public class PropostaStatusResponse {

	@NotBlank
	private String nome;

	@Enumerated(EnumType.STRING)
	private StatusProposta status;
	
	public PropostaStatusResponse(Proposta proposta) {
		this.nome = proposta.getNome();
		this.status = proposta.getStatus();
	}
	
	public String getNome() {
		return nome;
	}
	
	public StatusProposta getStatus() {
		return status;
	}
}
