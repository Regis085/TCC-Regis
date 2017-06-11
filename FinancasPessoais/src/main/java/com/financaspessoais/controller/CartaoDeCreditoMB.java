package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.service.impl.CartaoDeCreditoServiceImpl;

@ManagedBean
@ViewScoped
public class CartaoDeCreditoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private CartaoDeCreditoService cartaoDeCreditoService;
	private CartaoDeCredito cartaoDeCredito;
	private CartaoDeCredito cartaoDeCreditoSelecionado;

	public void prepararCadastro() {
		if (this.cartaoDeCredito == null)
			this.cartaoDeCredito = new CartaoDeCredito();
	}

	public String cadastrarCartaoDeCredito() {
		String retorno;
		boolean inseridoComSucesso = getCartaoDeCreditoService().criarOuAtualizar(cartaoDeCredito);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-cartao-credito?faces-redirect=true";
		} else {			
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		getCartaoDeCreditoService().remover(this.cartaoDeCreditoSelecionado);
		this.getCartaoDeCreditosDoUsuario();
	}

	// Getters e Setters

	public CartaoDeCredito getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public CartaoDeCredito getCartaoDeCreditoSelecionado() {
		return cartaoDeCreditoSelecionado;
	}

	public void setCartaoDeCreditoSelecionado(CartaoDeCredito cartaoDeCreditoSelecionado) {
		this.cartaoDeCreditoSelecionado = cartaoDeCreditoSelecionado;
	}
	
	public List<CartaoDeCredito> getCartaoDeCreditosDoUsuario() {
		return getCartaoDeCreditoService().listarPorUsuario();
	}
	
	private CartaoDeCreditoService getCartaoDeCreditoService() {
		if (this.cartaoDeCreditoService == null)
			this.cartaoDeCreditoService = new CartaoDeCreditoServiceImpl();
		return cartaoDeCreditoService;
	}
}
