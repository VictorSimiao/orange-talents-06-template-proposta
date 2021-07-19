package br.com.zupacademy.victor.proposta.proposta.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
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
	
	@Deprecated
	public Endereco() {
		
	}

	public Endereco(@NotBlank String cep, @NotBlank String rua, @NotBlank String numero, String complemento,
			@NotBlank String bairro, @NotBlank String cidade) {
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
	}

}
