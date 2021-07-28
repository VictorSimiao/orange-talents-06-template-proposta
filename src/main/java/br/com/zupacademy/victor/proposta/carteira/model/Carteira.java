package br.com.zupacademy.victor.proposta.carteira.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteiraDigital carteira;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Cartao cartao;

	public Carteira(@NotBlank @Email String email, @NotNull TipoCarteiraDigital carteira, @NotNull Cartao cartao) {
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}

	public Integer getId() {
		return id;
	}

}
