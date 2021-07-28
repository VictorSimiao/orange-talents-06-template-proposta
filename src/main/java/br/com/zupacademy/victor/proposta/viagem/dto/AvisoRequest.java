package br.com.zupacademy.victor.proposta.viagem.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisoRequest {

	@NotBlank
	private String destino;

	@NotNull
	@FutureOrPresent
	private LocalDate validoAte;

	public AvisoRequest(AvisoViagemRequest request) {
		this.destino = request.getDestinoViagem();
		this.validoAte = request.getDataTermio();
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}
}
