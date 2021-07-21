package br.com.zupacademy.victor.proposta.proposta.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.victor.proposta.proposta.clientfeign.AnaliseDePropostaClient;
import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaResponse;
import br.com.zupacademy.victor.proposta.proposta.dto.PropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.model.StatusProposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;
import feign.FeignException;

@RestControllerAdvice
@RequestMapping("api/propostas")
public class NovaPropostaController {

	private PropostaRepository propostaRepository;
	private AnaliseDePropostaClient analiseCliente;

	public NovaPropostaController(PropostaRepository propostaRepository, AnaliseDePropostaClient analiseCliente) {
		this.propostaRepository = propostaRepository;
		this.analiseCliente = analiseCliente;
	}

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		Proposta novaProposta = request.tomodel(propostaRepository);

		try {
			AnalisePropostaResponse analiseResponse = analiseCliente
					.realizarAnalise(new AnalisePropostaRequest(novaProposta));
			novaProposta.setStatus(analiseResponse.getStatusDaProposta());
		} catch (FeignException exception) {
			novaProposta.setStatus(StatusProposta.NAO_ELEGIVEL);
			propostaRepository.save(novaProposta);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		propostaRepository.save(novaProposta);

		URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
