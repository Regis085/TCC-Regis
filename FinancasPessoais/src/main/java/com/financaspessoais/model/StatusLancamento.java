package com.financaspessoais.model;

public enum StatusLancamento {
	PENDENTE(1, "Pendente"), REALIZADO(2, "Realizado");

	private Integer codigo;
	private String descricao;

	StatusLancamento(Integer codigo, String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}
}
