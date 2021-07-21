package br.com.zupacademy.victor.proposta.proposta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.victor.proposta.proposta.model.Proposta;

public class AnalisePropostaRequest {
	@NotBlank
	private String documento;
	@NotBlank
	private String nome;
	@NotNull
	private Integer idProposta;

	public AnalisePropostaRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdProposta() {
		return idProposta;
	}

}
