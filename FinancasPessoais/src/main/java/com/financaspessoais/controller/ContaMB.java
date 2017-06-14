package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.impl.ContaServiceImpl;

@ManagedBean
@ViewScoped
public class ContaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ContaService contaService;
	private Conta conta;
	private Conta contaSelecionada;
	private List<Conta> contasDoUsuario;

	public void prepararCadastro() {
		if (this.conta == null)
			this.conta = new Conta();
	}

	public String cadastrarConta() {
		String retorno = null;
		boolean inseridoComSucesso = this.getContaService().criarOuAtualizar(conta);
		if (inseridoComSucesso)
			retorno = "/pages/lista-conta?faces-redirect=true";
		return retorno;
	}

	public void excluir() {
		this.getContaService().remover(this.contaSelecionada);
		this.contasDoUsuario = null;
		this.getContasDoUsuario();
	}

	// Getters e Setters

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoConta[] getTiposConta() {
		return TipoConta.values();
	}

	public List<Conta> getContas() {
		return getContaService().listarContas();
	}

	public List<Conta> getContasDoUsuario() {
		if (contasDoUsuario == null)
			contasDoUsuario = getContaService().listarNaoBancariasPorUsuario();
		return contasDoUsuario;
	}
	
	private ContaService getContaService() {
		if (this.contaService == null)
			this.contaService = new ContaServiceImpl();
		return contaService;
	}
}
