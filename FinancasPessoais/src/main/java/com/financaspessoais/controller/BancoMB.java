package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.Banco;
import com.financaspessoais.service.BancoService;
import com.financaspessoais.service.impl.BancoServiceImpl;

@ManagedBean
@ViewScoped
public class BancoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BancoService bancoService;
	private Banco banco;
	private Banco bancoSelecionado;

	public void prepararCadastro() {
		if (this.banco == null)
			this.banco = new Banco();
	}

	public String cadastrarBanco() {
		String retorno = null;
		boolean inseridoComSucesso = this.getBancoService().criarOuAtualizar(banco);
		if (inseridoComSucesso)
			retorno = "/pages/lista-banco?faces-redirect=true";
		return retorno;
	}

	public void excluir() {
		this.getBancoService().remover(this.bancoSelecionado);
		this.getBancosDoUsuario();
	}

	// Getters e Setters

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Banco getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}
	
	public List<Banco> getBancosDoUsuario() {
		return getBancoService().listarPorUsuario();
	}
	
	private BancoService getBancoService() {
		if (this.bancoService == null)
			this.bancoService = new BancoServiceImpl();
		return bancoService;
	}
}
