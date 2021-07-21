package br.com.zupacademy.victor.proposta.proposta.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.zupacademy.victor.proposta.proposta.model.ResultadoSolicitacao;
import br.com.zupacademy.victor.proposta.proposta.model.StatusProposta;

public class AnalisePropostaResponse {

	private String documento;
	private String nome;
	private Integer idProposta;
	@Enumerated(EnumType.STRING)
	private ResultadoSolicitacao resultadoSolicitacao;

	public AnalisePropostaResponse(String documento, String nome, Integer idProposta,
			ResultadoSolicitacao resultadoSolicitacao) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public StatusProposta getStatusDaProposta() {
		return resultadoSolicitacao.getStatusProposta();
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
