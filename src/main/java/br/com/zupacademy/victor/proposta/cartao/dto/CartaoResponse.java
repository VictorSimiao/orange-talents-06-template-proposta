package br.com.zupacademy.victor.proposta.cartao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;

public class CartaoResponse {

	@NotBlank
	private String numeroCartao;
	@NotNull
	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;
	@NotNull
	private BigDecimal limite;
	@NotNull
	private Integer idProposta;

	public CartaoResponse(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
			@NotNull BigDecimal limite, @NotNull Integer idProposta) {
		this.numeroCartao = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Integer getIdProposta() {
		return idProposta;
	}

	public Cartao toModel(PropostaRepository repository) {
		Proposta proposta = repository.findById(idProposta).get();
		Assert.notNull(proposta, "A Proposta precisa exitir para gerar um cartão");
		return new Cartao(numeroCartao, emitidoEm, titular, limite);
	}

}
