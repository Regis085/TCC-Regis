package com.financaspessoais.model;

public enum SimNao {
	SIM("S", "Sim"), NAO("N", "Não");

	private String codigo;
	private String descricao;

	SimNao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}
}