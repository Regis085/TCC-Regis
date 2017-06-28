package com.financaspessoais.model;

public enum StatusItemLancamentoCartao {
	PREVISTO(1, "Previsto"), REAL(2, "Real");

	private Integer codigo;
	private String descricao;

	private StatusItemLancamentoCartao(Integer codigo, String descricao) {
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