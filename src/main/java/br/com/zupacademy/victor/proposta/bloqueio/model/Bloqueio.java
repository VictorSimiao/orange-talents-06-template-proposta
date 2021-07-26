package br.com.zupacademy.victor.proposta.bloqueio.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;

@Entity
public class Bloqueio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String idCliente;
	
	@NotBlank
	private String userAgent;
	
	@NotNull
	private LocalDateTime bloqueadoEm = LocalDateTime.now();
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Cartao cartao;
	
	@Deprecated
	public Bloqueio() {
		
	}
	public Bloqueio(@NotBlank String idCliente, @NotBlank String userAgent, @NotNull Cartao cartao) {
		this.idCliente = idCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}
	
	

}
