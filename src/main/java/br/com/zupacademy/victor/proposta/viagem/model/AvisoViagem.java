package br.com.zupacademy.victor.proposta.viagem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String idCliente;

	@NotBlank
	private String userAgent;

	@NotBlank
	private String destinoViagem;

	@NotNull
	@FutureOrPresent
	private LocalDate dataTermio;

	@NotNull
	private LocalDateTime registradoEm = LocalDateTime.now();

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Cartao cartao;

	public AvisoViagem(@NotBlank String idCliente, @NotBlank String userAgent, @NotBlank String destinoViagem,
			@NotNull @FutureOrPresent LocalDate dataTermio, @NotNull Cartao cartao) {
		this.idCliente = idCliente;
		this.userAgent = userAgent;
		this.destinoViagem = destinoViagem;
		this.dataTermio = dataTermio;
		this.cartao = cartao;
	}

}
