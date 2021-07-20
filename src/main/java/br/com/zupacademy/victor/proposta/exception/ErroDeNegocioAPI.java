package br.com.zupacademy.victor.proposta.exception;

public class ErroDeNegocioAPI {

	private String mensagem;

	public ErroDeNegocioAPI(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
