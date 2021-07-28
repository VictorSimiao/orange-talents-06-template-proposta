package br.com.zupacademy.victor.proposta.carteira.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.victor.proposta.cartao.feign.CartaoFeignApi;
import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.cartao.repository.CartaoRepository;
import br.com.zupacademy.victor.proposta.carteira.dto.CarteiraRequest;
import br.com.zupacademy.victor.proposta.carteira.model.Carteira;
import br.com.zupacademy.victor.proposta.carteira.repository.CarteiraRepository;
import feign.FeignException;

@RestController
@RequestMapping("api/cartoes")
public class AssociaCartaoController {

	private CartaoRepository cartaoRepository;
	private CarteiraRepository carteiraRepository;
	private CartaoFeignApi cartaoFeignApi;

	public AssociaCartaoController(CartaoRepository cartaoRepository, CarteiraRepository carteiraRepository,
			CartaoFeignApi cartaoFeignApi) {
		this.cartaoRepository = cartaoRepository;
		this.carteiraRepository = carteiraRepository;
		this.cartaoFeignApi = cartaoFeignApi;
	}

	@PostMapping("/{id}/carteiras")
	public ResponseEntity<?> associaCartaoCarteiraPaypal(@PathVariable("id") Integer id,
			@RequestBody @Valid CarteiraRequest request, UriComponentsBuilder uriBuilder) {

		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		if (carteiraRepository.existsByCartaoAndCarteira(possivelCartao.get(), request.getCarteira())) {
			return ResponseEntity.unprocessableEntity().body("Esse cartão já está associado a carteira");
		}
		try {

			cartaoFeignApi.associaCartao(possivelCartao.get().getNumeroCartao(), request);
			
			Carteira carteiraDigital = request.toModel(possivelCartao.get());
			carteiraRepository.save(carteiraDigital);
			
			URI uri = uriBuilder.path("/api/carteiras/{id}").buildAndExpand(carteiraDigital.getId()).toUri();
			return ResponseEntity.created(uri).build();

		} catch (FeignException exception) {
			return ResponseEntity.unprocessableEntity().body("Erro ao associar o cartão");
		}
	}
}
