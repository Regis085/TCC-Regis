package com.financaspessoais.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conta_bancaria")
public class ContaBancaria extends Conta {

	@Column(name = "numero_agencia", nullable = true, length = 10)
	private String numeroAgencia;

	@Column(name = "numero_conta", nullable = true, length = 15)
	private String numeroConta;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_banco")
	private Banco banco;

	@Column(name = "telefone_agencia", nullable = true, length = 15)
	private String telefoneAgencia;

	@Column(name = "endereco_agencia", nullable = true, length = 255)
	private String enderecoAgencia;

	@Column(name = "nome_gerente", nullable = true, length = 80)
	private String nomeGerente;

	@Column(name = "telefone_gerente", nullable = true, length = 15)
	private String telefoneGerente;

	@Column(name = "email_gerente", nullable = true, length = 100)
	private String emailGerente;

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getTelefoneAgencia() {
		return telefoneAgencia;
	}

	public void setTelefoneAgencia(String telefoneAgencia) {
		this.telefoneAgencia = telefoneAgencia;
	}

	public String getEnderecoAgencia() {
		return enderecoAgencia;
	}

	public void setEnderecoAgencia(String enderecoAgencia) {
		this.enderecoAgencia = enderecoAgencia;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public String getTelefoneGerente() {
		return telefoneGerente;
	}

	public void setTelefoneGerente(String telefoneGerente) {
		this.telefoneGerente = telefoneGerente;
	}

	public String getEmailGerente() {
		return emailGerente;
	}

	public void setEmailGerente(String emailGerente) {
		this.emailGerente = emailGerente;
	}
}
