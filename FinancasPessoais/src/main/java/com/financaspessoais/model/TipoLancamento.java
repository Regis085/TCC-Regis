package com.financaspessoais.model;

public enum TipoLancamento {
	RECEITA(1, "Receita", "(+)"), DESPESA(2, "Despesa", "(-)");

	private Integer codigo;
	private String descricao;
	private String sinal;

	TipoLancamento(Integer codigo, String descricao, String sinal) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.sinal = sinal;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getSinal() {
		return sinal;
	}
}
