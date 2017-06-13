package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.StatusFaturaCartao;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.service.impl.CartaoDeCreditoServiceImpl;
import com.financaspessoais.service.impl.FaturaCartaoServiceImpl;

@ManagedBean
@ViewScoped
public class FaturaCartaoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private FaturaCartaoService faturaCartaoService;
	private FaturaCartao faturaCartao;
	private FaturaCartao faturaCartaoSelecionada;
	private CartaoDeCreditoService cartaoDeCreditoService;
	private List<CartaoDeCredito> listaCartaoDeCredito;
	
	public String getChaveComposta() {
		return faturaCartao.getId().getAno().toString();
	}

	public void prepararCadastro() {
		if (this.faturaCartao == null)
			this.faturaCartao = new FaturaCartao();
		if (this.listaCartaoDeCredito == null)
			listaCartaoDeCredito = this.getCartaoDeCreditoService().listarPorUsuario(); 
	}
	
	public StatusFaturaCartao[] getListaStatusFaturaCartao() {
		return StatusFaturaCartao.values();
	}

	public String cadastrarFaturaCartao() {
		String retorno;
		boolean inseridoComSucesso = this.getFaturaCartaoService().criarOuAtualizar(faturaCartao);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-fatura-cartao?faces-redirect=true";
		} else {			
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		this.getFaturaCartaoService().remover(this.faturaCartaoSelecionada);
		this.getListaFaturaCartaoDoUsuario();
	}

	// Getters e Setters

	public FaturaCartao getFaturaCartao() {
		return faturaCartao;
	}

	public void setFaturaCartao(FaturaCartao faturaCartao) {
		this.faturaCartao = faturaCartao;
	}

	public FaturaCartao getFaturaCartaoSelecionada() {
		return faturaCartaoSelecionada;
	}

	public void setFaturaCartaoSelecionada(FaturaCartao faturaCartaoSelecionado) {
		this.faturaCartaoSelecionada = faturaCartaoSelecionado;
	}
	
	public List<FaturaCartao> getListaFaturaCartaoDoUsuario() {
		return getFaturaCartaoService().listarPorUsuario();
	}
	
	public List<CartaoDeCredito> getListaCartaoDeCredito() {
		return listaCartaoDeCredito;
	}

	public void setListaCartaoDeCredito(List<CartaoDeCredito> listaCartaoDeCredito) {
		this.listaCartaoDeCredito = listaCartaoDeCredito;
	}

	private FaturaCartaoService getFaturaCartaoService() {
		if (this.faturaCartaoService == null)
			this.faturaCartaoService = new FaturaCartaoServiceImpl();
		return faturaCartaoService;
	}
	
	private CartaoDeCreditoService getCartaoDeCreditoService() {
		if (this.cartaoDeCreditoService == null)
			this.cartaoDeCreditoService = new CartaoDeCreditoServiceImpl();
		return cartaoDeCreditoService;
	}
}
