package br.com.zupacademy.victor.proposta.exception;

public class Problema {
	
	private String campo;
	private String mensagemErro;

	public Problema(String campo, String mensagemErro) {
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}
	
	public Problema(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}
}
