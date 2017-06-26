package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.service.LancamentoCartaoService;
import com.financaspessoais.service.impl.CartaoDeCreditoServiceImpl;
import com.financaspessoais.service.impl.EstabelecimentoServiceImpl;
import com.financaspessoais.service.impl.LancamentoCartaoServiceImpl;

@ManagedBean
@ViewScoped
public class LancamentoCartaoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LancamentoCartao lancamentoCartao;
	private LancamentoCartao lancamentoCartaoSelecionado;
	private List<CartaoDeCredito> listaCartaoDeCredito;
	
	private List<LancamentoCartao> listaLancamentoCartao;
	private List<Estabelecimento> listaEstabelecimento;
	
	private CartaoDeCreditoService cartaoDeCreditoService;
	private LancamentoCartaoService lancamentoCartaoService;
	private EstabelecimentoService estabelecimentoService;
	
	public void prepararCadastro() {
		if (lancamentoCartao == null)
			lancamentoCartao = new LancamentoCartao();
		
		if (listaEstabelecimento == null)
			listaEstabelecimento = getEstabelecimentoService().listarPorUsuario(); 
		
		if (this.listaCartaoDeCredito == null)
			listaCartaoDeCredito = this.getCartaoDeCreditoService().listarPorUsuario(); 
	}
	
	public List<CartaoDeCredito> filtrarListaCartoes(String query) {
        
		if (this.listaCartaoDeCredito == null)
			listaCartaoDeCredito = this.getCartaoDeCreditoService().listarPorUsuario();
        List<CartaoDeCredito> cartoesFiltrados = new ArrayList<CartaoDeCredito>();
         
        for (int i = 0; i < listaCartaoDeCredito.size(); i++) {
        	CartaoDeCredito cartao = listaCartaoDeCredito.get(i);
            if(cartao.getNome().toLowerCase().contains(query.toLowerCase())) {
                cartoesFiltrados.add(cartao);
            }
        }
         
        return cartoesFiltrados;
    }
	
	public List<Estabelecimento> filtrarListaEstabelecimentos(String query) {
		
		if (this.listaEstabelecimento == null)
			listaEstabelecimento = this.getEstabelecimentoService().listarPorUsuario();
		List<Estabelecimento> estabelecimentosFiltrados = new ArrayList<Estabelecimento>();
		
		for (int i = 0; i < listaEstabelecimento.size(); i++) {
			Estabelecimento estabelecimento = listaEstabelecimento.get(i);
			if(estabelecimento.getNome().toLowerCase().contains(query.toLowerCase())) {
				estabelecimentosFiltrados.add(estabelecimento);
			}
		}
		
		return estabelecimentosFiltrados;
	}
	
	private EstabelecimentoService getEstabelecimentoService() {
		if (this.estabelecimentoService == null)
			this.estabelecimentoService = new EstabelecimentoServiceImpl();
		return estabelecimentoService;
	}

	private CartaoDeCreditoService getCartaoDeCreditoService() {
		if (this.cartaoDeCreditoService == null)
			this.cartaoDeCreditoService = new CartaoDeCreditoServiceImpl();
		return cartaoDeCreditoService;
	}
	
	public String cadastrarLancamentoCartao() {
		String retorno;
		boolean inseridoComSucesso = this.getLancamentoCartaoService().criarOuAtualizar(lancamentoCartao);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-lancamento-cartao?faces-redirect=true";
		}
		else {			
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		this.getLancamentoCartaoService().remover(this.lancamentoCartaoSelecionado);
		this.listaLancamentoCartao = null;
		this.getListaLancamentoCartao();
	}
	
	private LancamentoCartaoService getLancamentoCartaoService() {
		if (this.lancamentoCartaoService == null)
			this.lancamentoCartaoService = new LancamentoCartaoServiceImpl();
		return lancamentoCartaoService;
	}

	public LancamentoCartao getLancamentoCartao() {
		return lancamentoCartao;
	}

	public void setLancamentoCartao(LancamentoCartao lancamentoCartao) {
		this.lancamentoCartao = lancamentoCartao;
	}

	public LancamentoCartao getLancamentoCartaoSelecionado() {
		return lancamentoCartaoSelecionado;
	}

	public void setLancamentoCartaoSelecionado(LancamentoCartao lancamentoCartaoSelecionado) {
		this.lancamentoCartaoSelecionado = lancamentoCartaoSelecionado;
	}

	public List<CartaoDeCredito> getListaCartaoDeCredito() {
		return listaCartaoDeCredito;
	}

	public void setListaCartaoDeCredito(List<CartaoDeCredito> listaCartaoDeCredito) {
		this.listaCartaoDeCredito = listaCartaoDeCredito;
	}

	public List<LancamentoCartao> getListaLancamentoCartao() {
		if (listaLancamentoCartao == null) {
			listaLancamentoCartao = getLancamentoCartaoService().listarPorUsuario();
		}
		return listaLancamentoCartao;
	}

	public void setListaLancamentoCartao(List<LancamentoCartao> listaLancamentoCartao) {
		this.listaLancamentoCartao = listaLancamentoCartao;
	}

	public List<Estabelecimento> getListaEstabelecimento() {
		return listaEstabelecimento;
	}

	public void setListaEstabelecimento(List<Estabelecimento> listaEstabelecimento) {
		this.listaEstabelecimento = listaEstabelecimento;
	}
}
