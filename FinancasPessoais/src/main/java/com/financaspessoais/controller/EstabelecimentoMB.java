package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.service.impl.EstabelecimentoServiceImpl;

@ManagedBean
@ViewScoped
public class EstabelecimentoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EstabelecimentoService estabelecimentoService;
	private Estabelecimento estabelecimento;
	private Estabelecimento estabelecimentoSelecionado;
	private List<Estabelecimento> estabelecimentosDoUsuario;

	public void prepararCadastro() {
		if (this.estabelecimento == null)
			this.estabelecimento = new Estabelecimento();
	}

	public String cadastrarEstabelecimento() {
		String retorno = null;
		boolean inseridoComSucesso = this.getEstabelecimentoService().criarOuAtualizar(estabelecimento);
		if (inseridoComSucesso)
			retorno = "/pages/lista-estabelecimento?faces-redirect=true";
		return retorno;
	}

	public void excluir() {
		this.getEstabelecimentoService().remover(this.estabelecimentoSelecionado);
		this.estabelecimentosDoUsuario = null;
		this.getEstabelecimentosDoUsuario();
	}

	// Getters e Setters
	
	public List<Estabelecimento> getEstabelecimentosDoUsuario() {
		if (estabelecimentosDoUsuario == null)
			estabelecimentosDoUsuario = getEstabelecimentoService().listarPorUsuario();
		return estabelecimentosDoUsuario; 
	}
	
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimentoSelecionado() {
		return estabelecimentoSelecionado;
	}

	public void setEstabelecimentoSelecionado(Estabelecimento estabelecimentoSelecionado) {
		this.estabelecimentoSelecionado = estabelecimentoSelecionado;
	}
	
	private EstabelecimentoService getEstabelecimentoService() {
		if (this.estabelecimentoService == null)
			this.estabelecimentoService = new EstabelecimentoServiceImpl();
		return estabelecimentoService;
	}
}
