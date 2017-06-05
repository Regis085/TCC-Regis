package com.financaspessoais.model;

public enum TipoConta {
	BANCARIA("Bancária"), OUTRO("Outro");

	private String descricao;

	TipoConta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}