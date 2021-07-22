package br.com.zupacademy.victor.proposta.cartao.dto;

import br.com.zupacademy.victor.proposta.proposta.model.Proposta;

public class CartaoRequest {

	private String documento;
	private String nome;
	private Integer idProposta;

	public CartaoRequest(Proposta proposta) {
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
