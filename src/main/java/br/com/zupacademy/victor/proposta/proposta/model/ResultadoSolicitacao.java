package br.com.zupacademy.victor.proposta.proposta.model;

public enum ResultadoSolicitacao {
	COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
	SEM_RESTRICAO(StatusProposta.ELEGIVEL);
	
	StatusProposta statusProposta;

	private ResultadoSolicitacao(StatusProposta statusProposta) {
		this.statusProposta = statusProposta;
	}
	
	public StatusProposta getStatusProposta() {
		return statusProposta;
	}
	
}
