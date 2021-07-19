package br.com.zupacademy.victor.proposta.proposta.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.victor.proposta.proposta.model.Endereco;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.validation.annotations.CPForCNPJ;

public class PropostaRequest {

	@CPForCNPJ
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private EnderecoRequest endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	public PropostaRequest(@CPForCNPJ String documento, @NotBlank @Email String email, @NotBlank String nome,
			@Valid @NotNull EnderecoRequest endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public Proposta tomodel() {
		Endereco enderecoProposta = endereco.toModel();
		Assert.notNull(enderecoProposta, "Proposta não pode ter um endereço vazio");
		return new Proposta(documento,email,nome,salario,enderecoProposta);
	}

	

}
