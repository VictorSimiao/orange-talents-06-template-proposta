package br.com.zupacademy.victor.proposta.proposta.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.victor.proposta.proposta.dto.PropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;

@RestControllerAdvice
@RequestMapping("api/propostas")
public class NovaPropostaController {

	PropostaRepository propostaRepository;

	public NovaPropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		Proposta novaProposta = request.tomodel(propostaRepository);
		propostaRepository.save(novaProposta);
		URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
