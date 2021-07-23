package br.com.zupacademy.victor.proposta.biometria.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String fingerPrint;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Cartao cartao;

	@NotNull
	private LocalDateTime criadaEm = LocalDateTime.now();

	public Biometria(@NotBlank String fingerPrint, @NotNull Cartao cartao) {
		this.fingerPrint = fingerPrint;
		this.cartao = cartao;
	}

	public Integer getId() {
		return id;
	}
}
