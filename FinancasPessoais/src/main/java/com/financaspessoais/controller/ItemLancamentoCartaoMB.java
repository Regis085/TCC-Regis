package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.service.ItemLancamentoCartaoService;
import com.financaspessoais.service.LancamentoCartaoService;
import com.financaspessoais.service.impl.CartaoDeCreditoServiceImpl;
import com.financaspessoais.service.impl.FaturaCartaoServiceImpl;
import com.financaspessoais.service.impl.ItemLancamentoCartaoServiceImpl;
import com.financaspessoais.service.impl.LancamentoCartaoServiceImpl;

@ManagedBean
@ViewScoped
public class ItemLancamentoCartaoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ItemLancamentoCartao itemLancamentoCartao;
	private ItemLancamentoCartao itemLancamentoCartaoSelecionado;
	private List<ItemLancamentoCartao> listaItemLancamentoCartao;
	
	private List<LancamentoCartao> listaLancamentoCartao;
	private List<FaturaCartao> listaFaturaCartao;
	private List<CartaoDeCredito> listaCartaoDeCredito;
	
	private ItemLancamentoCartaoService itemLancamentoCartaoService;
	private LancamentoCartaoService lancamentoCartaoService;
	private FaturaCartaoService faturaCartaoService;
	private CartaoDeCreditoService cartaoDeCreditoService;
	
	//--------------------------------------------------------------------------------------
	
	public void prepararCadastro() {
		if (itemLancamentoCartao == null)
			itemLancamentoCartao = new ItemLancamentoCartao();
//		if (listaLancamentoCartao == null)
//			listaLancamentoCartao = getLancamentoCartaoService().listarPorUsuario();
//		if (listaFaturaCartao == null)
//			listaFaturaCartao = getFaturaCartaoService().listarPorUsuario();
		if (listaCartaoDeCredito == null)
			listaCartaoDeCredito = getCartaoDeCreditoService().listarPorUsuario();
	}
	
	public void limparListasFaturaELancamento() {
		listaLancamentoCartao = null;
		listaFaturaCartao = null;
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
	
	public List<LancamentoCartao> filtrarListaLancamentoCartao(String query) {
        
		if (this.listaLancamentoCartao == null)
			if (itemLancamentoCartao.getCartaoDeCredito() != null && itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito() != null)
				listaLancamentoCartao = this.getLancamentoCartaoService().listarPorUsuarioECartaoDeCredito(itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito());
				
        List<LancamentoCartao> lancamentosCartaoFiltrados = new ArrayList<LancamentoCartao>();
        
        if (listaLancamentoCartao != null) {
        	for (int i = 0; i < listaLancamentoCartao.size(); i++) {
        		LancamentoCartao lancCartao = listaLancamentoCartao.get(i);
        		if (lancCartao.getNome().toLowerCase().contains(query.toLowerCase())) {
        			lancamentosCartaoFiltrados.add(lancCartao);
        		}
        	}
        }
         
        return lancamentosCartaoFiltrados;
    }
	
	public List<FaturaCartao> filtrarListaFaturaCartao(String query) {
		
		if (this.listaFaturaCartao == null)
			if (itemLancamentoCartao.getCartaoDeCredito() != null && itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito() != null)
				listaFaturaCartao = this.getFaturaCartaoService().listarPorUsuarioECartaoDeCredito(itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito());
			else
				listaFaturaCartao = null;
		
		List<FaturaCartao> faturasCartaoFiltradas = new ArrayList<FaturaCartao>();
		
		if (listaFaturaCartao != null) {
			for (int i = 0; i < listaFaturaCartao.size(); i++) {
				FaturaCartao faturaCartao = listaFaturaCartao.get(i);
				if (faturaCartao.getNome().toLowerCase().contains(query.toLowerCase())) {
					faturasCartaoFiltradas.add(faturaCartao);
				}
			}
		}
		
		return faturasCartaoFiltradas;
	}
	
	private LancamentoCartaoService getLancamentoCartaoService() {
		if (this.lancamentoCartaoService == null)
			this.lancamentoCartaoService = new LancamentoCartaoServiceImpl();
		return lancamentoCartaoService;
	}
	
	private FaturaCartaoService getFaturaCartaoService() {
		if (this.faturaCartaoService == null)
			this.faturaCartaoService = new FaturaCartaoServiceImpl();
		return faturaCartaoService;
	}
	
	public String cadastrarItemLancamentoCartao() {
		String retorno;
		boolean inseridoComSucesso = this.getItemLancamentoCartaoService().criarOuAtualizar(itemLancamentoCartao);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-item-lancamento-cartao?faces-redirect=true";
		}
		else {			
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		this.getItemLancamentoCartaoService().remover(this.itemLancamentoCartaoSelecionado);
		this.listaItemLancamentoCartao = null;
		this.getListaItemLancamentoCartao();
	}
	
	private ItemLancamentoCartaoService getItemLancamentoCartaoService() {
		if (this.itemLancamentoCartaoService == null)
			this.itemLancamentoCartaoService = new ItemLancamentoCartaoServiceImpl();
		return itemLancamentoCartaoService;
	}
	
	private CartaoDeCreditoService getCartaoDeCreditoService() {
		if (this.cartaoDeCreditoService == null)
			this.cartaoDeCreditoService = new CartaoDeCreditoServiceImpl();
		return cartaoDeCreditoService;
	}

	public ItemLancamentoCartao getItemLancamentoCartao() {
		return itemLancamentoCartao;
	}

	public void setItemLancamentoCartao(ItemLancamentoCartao itemLancamentoCartao) {
		this.itemLancamentoCartao = itemLancamentoCartao;
	}

	public ItemLancamentoCartao getItemLancamentoCartaoSelecionado() {
		return itemLancamentoCartaoSelecionado;
	}

	public void setItemLancamentoCartaoSelecionado(ItemLancamentoCartao itemLancamentoCartaoSelecionado) {
		this.itemLancamentoCartaoSelecionado = itemLancamentoCartaoSelecionado;
	}

	public List<ItemLancamentoCartao> getListaItemLancamentoCartao() {
		if (listaItemLancamentoCartao == null) {
			listaItemLancamentoCartao = getItemLancamentoCartaoService().listarPorUsuario();
		}
		return listaItemLancamentoCartao;
	}

	public void setListaItemLancamentoCartao(List<ItemLancamentoCartao> listaItemLancamentoCartao) {
		this.listaItemLancamentoCartao = listaItemLancamentoCartao;
	}

	public List<LancamentoCartao> getListaLancamentoCartao() {
		return listaLancamentoCartao;
	}

	public void setListaLancamentoCartao(List<LancamentoCartao> listaLancamentoCartao) {
		this.listaLancamentoCartao = listaLancamentoCartao;
	}

	public List<FaturaCartao> getListaFaturaCartao() {
		return listaFaturaCartao;
	}

	public void setListaFaturaCartao(List<FaturaCartao> listaFaturaCartao) {
		this.listaFaturaCartao = listaFaturaCartao;
	}
}
