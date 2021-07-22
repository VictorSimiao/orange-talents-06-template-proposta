package br.com.zupacademy.victor.proposta.cartao.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String numeroCartao;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotBlank
	private String titular;
	
	@NotNull
	private BigDecimal limite;

	@Deprecated
	public Cartao() {
		
	}
	public Cartao(@NotBlank String numeroCartao, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
			@NotNull BigDecimal limite) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
	}

}
