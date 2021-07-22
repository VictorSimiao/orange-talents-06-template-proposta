package br.com.zupacademy.victor.proposta.proposta.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zupacademy.victor.proposta.proposta.dto.PropostaStatusResponse;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;

@RestControllerAdvice
@RequestMapping("api/propostas")
public class BuscarPropostaController {

	private PropostaRepository propostaRepository;

	public BuscarPropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaStatusResponse> consultar(@PathVariable Integer id) {

		Optional<Proposta> possivelProposta = propostaRepository.findById(id);

		if (possivelProposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new PropostaStatusResponse(possivelProposta.get()));
	}
}
