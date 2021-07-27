package br.com.zupacademy.victor.proposta.biometria.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.victor.proposta.biometria.dto.BiometriaRequest;
import br.com.zupacademy.victor.proposta.biometria.model.Biometria;
import br.com.zupacademy.victor.proposta.biometria.repository.BiometriaRepository;
import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.cartao.repository.CartaoRepository;

@RestControllerAdvice
@RequestMapping("api/propostas")
public class NovaBiometriaController {

	private CartaoRepository cartaoRepository;
	private BiometriaRepository biometriaRepository;

	public NovaBiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
		this.cartaoRepository = cartaoRepository;
		this.biometriaRepository = biometriaRepository;
	}

	@PostMapping("/{id}/biometria")
	public ResponseEntity<?> cadastrar(@PathVariable("id") Integer id,
			@RequestBody @Valid BiometriaRequest request,UriComponentsBuilder uriBuilder) {
		
		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
		
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Biometria novaBiometria = request.toModel(possivelCartao.get());
		biometriaRepository.save(novaBiometria);
		
		URI uri = uriBuilder.path("/api/propostas/biometrias/{id}").buildAndExpand(novaBiometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
