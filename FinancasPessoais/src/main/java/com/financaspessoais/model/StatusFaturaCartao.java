package com.financaspessoais.model;

public enum StatusFaturaCartao {
	PAGO(1, "Pago"), PENDENTE(2, "Pendente"), ATRASADO(3, "Atrasado"), PARCIALMENTE_PAGO(4, "Parcialmente Pago");

	private Integer codigo;

	private String descricao;

	private StatusFaturaCartao(Integer codigo, String descricao) {
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