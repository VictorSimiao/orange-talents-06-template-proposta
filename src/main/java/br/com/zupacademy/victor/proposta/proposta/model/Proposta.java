package br.com.zupacademy.victor.proposta.proposta.model;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.victor.proposta.validation.annotations.CPForCNPJ;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CPForCNPJ
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal salario;

	@NotNull
	@Embedded
	private Endereco enderecoProposta;

	@Enumerated(EnumType.STRING)
	private StatusProposta status;

	@Deprecated
	public Proposta() {

	}

	public Proposta(@CPForCNPJ String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotNull @Positive BigDecimal salario, @NotNull Endereco enderecoProposta) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.enderecoProposta = enderecoProposta;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
	
	public void setStatus(StatusProposta status) {
		this.status = status;
	}
}
