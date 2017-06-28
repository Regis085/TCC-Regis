package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.service.ItemLancamentoCartaoService;
import com.financaspessoais.service.LancamentoCartaoService;
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
	
	private ItemLancamentoCartaoService itemLancamentoCartaoService;
	private LancamentoCartaoService lancamentoCartaoService;
	private FaturaCartaoService faturaCartaoService;
	
	//--------------------------------------------------------------------------------------
	
	public void prepararCadastro() {
		if (itemLancamentoCartao == null)
			itemLancamentoCartao = new ItemLancamentoCartao();
		if (listaLancamentoCartao == null)
			listaLancamentoCartao = getLancamentoCartaoService().listarPorUsuario();
		if (listaFaturaCartao == null)
			listaFaturaCartao = getFaturaCartaoService().listarPorUsuario();
	}
	
	public List<LancamentoCartao> filtrarListaLancamentoCartao(String query) {
        
		if (this.listaLancamentoCartao == null)
			listaLancamentoCartao = this.getLancamentoCartaoService().listarPorUsuario();
        List<LancamentoCartao> lancamentosCartaoFiltrados = new ArrayList<LancamentoCartao>();
         
        for (int i = 0; i < listaLancamentoCartao.size(); i++) {
        	LancamentoCartao lancCartao = listaLancamentoCartao.get(i);
            if(lancCartao.getNome().toLowerCase().contains(query.toLowerCase())) {
                lancamentosCartaoFiltrados.add(lancCartao);
            }
        }
         
        return lancamentosCartaoFiltrados;
    }
	
	public List<FaturaCartao> filtrarListaFaturaCartao(String query) {
		
		if (this.listaFaturaCartao == null)
			listaFaturaCartao = this.getFaturaCartaoService().listarPorUsuario();
		List<FaturaCartao> faturasCartaoFiltradas = new ArrayList<FaturaCartao>();
		
		for (int i = 0; i < listaFaturaCartao.size(); i++) {
			FaturaCartao faturaCartao = listaFaturaCartao.get(i);
			if(faturaCartao.getNome().toLowerCase().contains(query.toLowerCase())) {
				faturasCartaoFiltradas.add(faturaCartao);
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
