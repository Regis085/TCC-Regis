package com.financaspessoais.model;

public enum MesDoAno {
	JAN("Janeiro", new Short("1")),
	FEV("Fevereiro", new Short("2")),
	MAR("Março", new Short("3")),
	ABR("Abril", new Short("4")),
	MAI("Maio", new Short("5")),
	JUN("Junho", new Short("6")),
	JUL("Julho", new Short("7")),
	AGO("Agosto", new Short("8")),
	SET("Setembro", new Short("9")),
	OUT("Outubro", new Short("10")),
	NOV("Novembro", new Short("11")),
	DEZ("Dezembro", new Short("12"));
	
	private String descricao;
	private Short numero;

	MesDoAno(String descricao, Short numero) {
		this.descricao = descricao;
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Short getNumero() {
		return numero;
	}
}
