package br.com.zupacademy.victor.proposta.carteira.model;

public enum TipoCarteiraDigital {
	PAYPAL("Paypal"),
	SAMSUNG_PAY("Samsung Pay");

	String tipo;

	TipoCarteiraDigital(String tipo) {
		this.tipo = tipo;
	}
}
