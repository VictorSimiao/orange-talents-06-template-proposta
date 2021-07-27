package br.com.zupacademy.victor.proposta.viagem.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.cartao.repository.AvisoViagemRepository;
import br.com.zupacademy.victor.proposta.cartao.repository.CartaoRepository;
import br.com.zupacademy.victor.proposta.viagem.dto.AvisoViagemRequest;
import br.com.zupacademy.victor.proposta.viagem.model.AvisoViagem;

@RestController
@RequestMapping("api/cartoes")
public class AvisoViagemController {

	private CartaoRepository cartaoRepository;
	private AvisoViagemRepository avisoViagemRepository;

	public AvisoViagemController(CartaoRepository cartaoRepository, AvisoViagemRepository avisoViagemRepository) {
		this.cartaoRepository = cartaoRepository;
		this.avisoViagemRepository = avisoViagemRepository;
	}

	@PostMapping("/{id}/viagens")
	public ResponseEntity<?> cadastraViagem(@PathVariable("id") Integer id, HttpServletRequest servletRequest,
			@RequestBody @Valid AvisoViagemRequest request) {

		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		if(possivelCartao.get().isBloqueado()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão encontra-se bloqueado");
		}
		
		String idCliente = servletRequest.getRemoteAddr();
		String userAgent = servletRequest.getHeader("User-Agent");

		AvisoViagem novoAvisoViagem = request.toModel(possivelCartao.get(), idCliente, userAgent);
		avisoViagemRepository.save(novoAvisoViagem);
		
		return ResponseEntity.ok().build();
	}
}
