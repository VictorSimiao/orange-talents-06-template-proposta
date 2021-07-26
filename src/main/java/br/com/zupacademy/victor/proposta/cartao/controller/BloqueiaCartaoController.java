package br.com.zupacademy.victor.proposta.cartao.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.proposta.cartao.dto.BloqueioRequest;
import br.com.zupacademy.victor.proposta.cartao.feign.CartaoFeignApi;
import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.cartao.repository.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("api/cartoes")
public class BloqueiaCartaoController {

	private CartaoRepository cartaoRepository;
	private CartaoFeignApi cartaoFeignApi;

	public BloqueiaCartaoController(CartaoRepository cartaoRepository, CartaoFeignApi cartaoFeignApi) {
		this.cartaoRepository = cartaoRepository;
		this.cartaoFeignApi = cartaoFeignApi;
	}

	@PostMapping("/{id}/bloqueios")
	public ResponseEntity<?> bloqueiaCartao(@PathVariable("id") String id, HttpServletRequest servletRequest) {

		Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(id);

		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		if (possivelCartao.get().isBloqueado()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		String idCliente = servletRequest.getRemoteAddr();
		String userAgent = servletRequest.getHeader("User-Agent");

		try {
			cartaoFeignApi.notificaSistemaLegado(possivelCartao.get().getNumeroCartao(),
					new BloqueioRequest("API-Proposta"));
			possivelCartao.get().efetuaBloqueio(idCliente, userAgent);
			cartaoRepository.save(possivelCartao.get());
			return ResponseEntity.ok().build();

		} catch (FeignException exception) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

	}
}
