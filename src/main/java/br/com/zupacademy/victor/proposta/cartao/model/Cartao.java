package br.com.zupacademy.victor.proposta.cartao.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.victor.proposta.bloqueio.model.Bloqueio;

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

	@Enumerated(EnumType.STRING)
	private StatusCartao status = StatusCartao.DESBLOQUEADO;

	@OneToMany(mappedBy = "cartao", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<Bloqueio> bloqueios = new ArrayList<Bloqueio>();

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

	public Boolean isBloqueado() {
		return this.status.equals(StatusCartao.BLOQUEADO);
	}

	public void efetuaBloqueio(String idCliente, String userAgente) {
		Assert.isTrue(!this.isBloqueado(), "É necessário que o cartão esteja bloqueado");
		if (!isBloqueado()) {
			bloqueios.add(new Bloqueio(idCliente, userAgente, this));
			this.status = StatusCartao.BLOQUEADO;
		}
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
}
