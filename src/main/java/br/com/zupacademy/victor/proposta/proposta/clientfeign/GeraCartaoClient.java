package br.com.zupacademy.victor.proposta.proposta.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.victor.proposta.cartao.dto.CartaoRequest;
import br.com.zupacademy.victor.proposta.cartao.dto.CartaoResponse;

@FeignClient(name = "cartao", url = "http://localhost:8888/")
public interface GeraCartaoClient {
	
	@PostMapping("api/cartoes")
	CartaoResponse solicitaCartao(@RequestBody CartaoRequest cartaoRequest);
}
