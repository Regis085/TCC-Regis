package com.financaspessoais.model;

public enum StatusItemLancamentoCartao {
	PREVISTO("Previsto"), REAL("Real");
	
	private String descricao;

	StatusItemLancamentoCartao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}