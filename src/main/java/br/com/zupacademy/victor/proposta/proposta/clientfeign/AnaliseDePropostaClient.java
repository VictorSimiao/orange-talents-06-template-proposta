package br.com.zupacademy.victor.proposta.proposta.clientfeign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaResponse;

@FeignClient(name = "analise", url ="${solicitacao.host}")
public interface AnaliseDePropostaClient {
	
	@PostMapping("${solicita.proposta}")
	AnalisePropostaResponse realizarAnalise(@RequestBody @Valid AnalisePropostaRequest request);
}
