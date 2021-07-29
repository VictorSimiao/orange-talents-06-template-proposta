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

import br.com.zupacademy.victor.proposta.infraestrutura.metrica.MetricaAplicacao;
import br.com.zupacademy.victor.proposta.proposta.clientfeign.AnaliseDePropostaClient;
import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaResponse;
import br.com.zupacademy.victor.proposta.proposta.dto.PropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.model.StatusProposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestControllerAdvice
@RequestMapping("api/propostas")
public class NovaPropostaController {

	private PropostaRepository propostaRepository;
	private AnaliseDePropostaClient analiseCliente;
	private MetricaAplicacao metricaAplicacao;
	private Tracer tracer;
	
	public NovaPropostaController(PropostaRepository propostaRepository, AnaliseDePropostaClient analiseCliente,
			MetricaAplicacao metricaAplicacao, Tracer tracer) {
		this.propostaRepository = propostaRepository;
		this.analiseCliente = analiseCliente;
		this.metricaAplicacao = metricaAplicacao;
		this.tracer = tracer;
	}




	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		
		Span activeSpan = tracer.activeSpan();
		activeSpan.setBaggageItem("user.email", "orangetalents@zup.com.br");
		
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
		metricaAplicacao.meuContador();
		propostaRepository.save(novaProposta);

		URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
