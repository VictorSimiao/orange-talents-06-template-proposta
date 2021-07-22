package br.com.zupacademy.victor.proposta.cartao.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.victor.proposta.cartao.dto.CartaoRequest;
import br.com.zupacademy.victor.proposta.cartao.dto.CartaoResponse;
import br.com.zupacademy.victor.proposta.cartao.repository.CartaoRepository;
import br.com.zupacademy.victor.proposta.proposta.clientfeign.GeraCartaoClient;
import br.com.zupacademy.victor.proposta.proposta.model.Proposta;
import br.com.zupacademy.victor.proposta.proposta.repository.PropostaRepository;
import feign.FeignException;

@Component
public class GeradoCartaoProposta {

	private PropostaRepository propostaRepository;
	private CartaoRepository cartaoRepository;
	private GeraCartaoClient geraCartaoClient;

	private final Logger log = LoggerFactory.getLogger(GeradoCartaoProposta.class);

	public GeradoCartaoProposta(PropostaRepository propostaRepository, CartaoRepository cartaoRepository,
			GeraCartaoClient geraCartaoClient) {
		this.propostaRepository = propostaRepository;
		this.cartaoRepository = cartaoRepository;
		this.geraCartaoClient = geraCartaoClient;
	}

	@Scheduled(cron = "5/10 * * * * * ")
	public void associarCartao() {
		List<Proposta> propostasElegiveis = propostaRepository.propostasElegiveisSemCartao();
		propostasElegiveis.forEach(proposta -> realizaAssociacao(proposta));
	}

	private void realizaAssociacao(Proposta proposta) {
		try {
			CartaoResponse cartaoResponse = geraCartaoClient.solicitaCartao(new CartaoRequest(proposta));
			Cartao cartao = cartaoResponse.toModel(propostaRepository);
			cartaoRepository.save(cartao);
			proposta.setCartao(cartao);
			propostaRepository.save(proposta);
			log.info("Cartão gerado e associado com sucesso");
		} catch (FeignException e) {
			log.error("Não foi possivel gerar um cartão. Erro:" + e.getCause());
		}
	}

}
