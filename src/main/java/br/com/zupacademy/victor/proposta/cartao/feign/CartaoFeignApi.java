package br.com.zupacademy.victor.proposta.cartao.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.victor.proposta.cartao.dto.BloqueioRequest;
import br.com.zupacademy.victor.proposta.cartao.dto.CartaoRequest;
import br.com.zupacademy.victor.proposta.cartao.dto.CartaoResponse;
import br.com.zupacademy.victor.proposta.cartao.dto.StatusCartaoResponse;
import br.com.zupacademy.victor.proposta.viagem.dto.AvisoRequest;
import br.com.zupacademy.victor.proposta.viagem.dto.AvisoResponse;

@FeignClient(name = "cartao", url = "${cartao.host}")
public interface CartaoFeignApi {

	@PostMapping("${proposta.cartao}")
	CartaoResponse solicitaCartao(@RequestBody CartaoRequest cartaoRequest);

	@PostMapping("${cartao.bloqueio}")
	StatusCartaoResponse notificaSistemaLegado(@PathVariable("id") String id, @RequestBody BloqueioRequest request);
	
	@PostMapping("${cartao.aviso}")
	AvisoResponse notificaSistemaBancarioViagem(@PathVariable("id") String id, @RequestBody @Valid AvisoRequest request);
}
