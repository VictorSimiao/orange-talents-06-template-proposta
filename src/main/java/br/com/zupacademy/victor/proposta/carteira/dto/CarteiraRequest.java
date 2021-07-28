package br.com.zupacademy.victor.proposta.carteira.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.carteira.model.Carteira;
import br.com.zupacademy.victor.proposta.carteira.model.TipoCarteiraDigital;

public class CarteiraRequest {
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteiraDigital carteira;

	public CarteiraRequest(@NotBlank @Email String email, @NotNull TipoCarteiraDigital carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public Carteira toModel(@NotNull Cartao cartao) {
		Assert.state(cartao!=null,"É necessário ter um cartão");
		return new Carteira(email,carteira,cartao);
	}
	
	public TipoCarteiraDigital getCarteira() {
		return carteira;
	}
	
}
