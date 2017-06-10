package com.financaspessoais.model;

public enum StatusFaturaCartao {
	PAGO("Pago"), PENDENTE("Pendente"), ATRASADO("Atrasado"), PARCIALMENTE_PAGO("Parcialmente Pago");
	
	private String descricao;

	StatusFaturaCartao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}