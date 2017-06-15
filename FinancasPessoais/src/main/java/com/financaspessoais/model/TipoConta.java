package com.financaspessoais.model;

public enum TipoConta {
	BANCARIA(1, "Bancária"), OUTRO(2, "Outro");

	private Integer codigo;
	private String descricao;

	TipoConta(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}
}