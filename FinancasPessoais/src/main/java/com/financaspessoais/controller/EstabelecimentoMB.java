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

	public void prepararCadastro() {
		if (this.estabelecimento == null)
			this.estabelecimento = new Estabelecimento();
	}

	public String cadastrarEstabelecimento() {
		String retorno;
		boolean inseridoComSucesso = getEstabelecimentoService().criarOuAtualizar(estabelecimento);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-estabelecimento?faces-redirect=true";
		} else {			
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		getEstabelecimentoService().remover(this.estabelecimentoSelecionado);
		this.getEstabelecimentosDoUsuario();
	}

	// Getters e Setters

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
	
	public List<Estabelecimento> getEstabelecimentosDoUsuario() {
		return getEstabelecimentoService().listarPorUsuario();
	}
	
	private EstabelecimentoService getEstabelecimentoService() {
		if (this.estabelecimentoService == null)
			this.estabelecimentoService = new EstabelecimentoServiceImpl();
		return estabelecimentoService;
	}
}
