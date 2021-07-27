package br.com.zupacademy.victor.proposta.proposta.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zupacademy.victor.proposta.proposta.dto.PropostaStatusResponse;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

@RestControllerAdvice
@RequestMapping("api/propostas")
public class BuscarPropostaController {

	private PropostaRepository propostaRepository;
	private MeterRegistry meterRegistry;

	public BuscarPropostaController(PropostaRepository propostaRepository, MeterRegistry meterRegistry) {
		this.propostaRepository = propostaRepository;
		this.meterRegistry = meterRegistry;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaStatusResponse> consultar(@PathVariable Integer id) {
		Collection<Tag> tags = new ArrayList<>();
		tags.add(Tag.of("emissora", "Mastercard"));
		tags.add(Tag.of("banco", "Itaú"));

		Timer timerConsultarProposta = this.meterRegistry.timer("consultar_proposta", tags);
		timerConsultarProposta.record(() -> {
			propostaRepository.findById(id);
		});

		Optional<Proposta> possivelProposta = propostaRepository.findById(id);

		if (possivelProposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new PropostaStatusResponse(possivelProposta.get()));
	}
}
