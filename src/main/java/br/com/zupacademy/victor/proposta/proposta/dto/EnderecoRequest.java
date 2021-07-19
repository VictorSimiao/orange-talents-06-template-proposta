package br.com.zupacademy.victor.proposta.proposta.dto;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.victor.proposta.proposta.model.Endereco;

public class EnderecoRequest {

	@NotBlank
	private String cep;
	@NotBlank
	private String rua;
	@NotBlank
	private String numero;
	private String complemento;
	@NotBlank
	private String bairro;
	@NotBlank
	private String cidade;

	public EnderecoRequest(@NotBlank String cep, @NotBlank String rua, @NotBlank String numero, String complemento,
			@NotBlank String bairro, @NotBlank String cidade) {
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public Endereco toModel() {
		return new Endereco(cep, rua, numero, complemento, bairro, cidade);
	}

}
