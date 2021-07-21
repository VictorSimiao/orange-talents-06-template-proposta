package br.com.zupacademy.victor.proposta.proposta.clientfeign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaRequest;
import br.com.zupacademy.victor.proposta.proposta.dto.AnalisePropostaResponse;

@FeignClient(name = "analise", url ="http://localhost:9999")
public interface AnaliseDePropostaClient {
	
	@RequestMapping(value="/api/solicitacao", method= RequestMethod.POST)
	AnalisePropostaResponse realizarAnalise(@RequestBody @Valid AnalisePropostaRequest request);
}
