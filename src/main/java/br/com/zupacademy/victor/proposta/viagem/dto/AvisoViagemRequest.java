package br.com.zupacademy.victor.proposta.viagem.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.viagem.model.AvisoViagem;

public class AvisoViagemRequest {

	@NotBlank
	private String destinoViagem;

	@NotNull
	@FutureOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataTermio;

	public AvisoViagemRequest() {
	}

	public AvisoViagemRequest(@NotBlank String destinoViagem, @NotNull @FutureOrPresent LocalDate dataTermio) {
		this.destinoViagem = destinoViagem;
		this.dataTermio = dataTermio;
	}

	public AvisoViagem toModel(@NotNull Cartao cartao, @NotBlank String idCliente, @NotBlank String userAgent) {
		return new AvisoViagem(idCliente, userAgent, destinoViagem, dataTermio, cartao);
	}

	public String getDestinoViagem() {
		return destinoViagem;
	}

}
