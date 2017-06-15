package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.service.impl.TipoReceitaServiceImpl;

@ManagedBean
@ViewScoped
public class TipoReceitaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoReceitaService tipoReceitaService;
	private TipoReceita tipoReceita;
	private TipoReceita tipoReceitaSelecionada;
	private List<TipoReceita> tiposReceitaDoUsuario;

	public void prepararCadastro() {
		if (this.tipoReceita == null)
			this.tipoReceita = new TipoReceita();
	}
	
	public void limparCamposPrevisao() {
		if (this.tipoReceita.getRecorrente().equals(SimNao.NAO.toString())) {
			this.tipoReceita.setValorPrevisto(null);
			this.tipoReceita.setDiaRecebimentoPrevisto(null);
		}
	}
	
	public String cadastrarTipoReceita() {
		String retorno = null;
		boolean inseridoComSucesso = this.getTipoReceitaService().criarOuAtualizar(tipoReceita);
		if (inseridoComSucesso)
			retorno = "/pages/lista-tipo-receita?faces-redirect=true";
		return retorno;
	}
	
	public void excluir() {
		this.getTipoReceitaService().remover(this.tipoReceitaSelecionada);
		this.tiposReceitaDoUsuario = null;
		this.getTiposReceitaDoUsuario();
	}

	// Getters e Setters
	public List<TipoReceita> getTiposReceitaDoUsuario() {
		if (tiposReceitaDoUsuario == null) {
			tiposReceitaDoUsuario = this.getTipoReceitaService().listarPorUsuario(); 
		}
		return tiposReceitaDoUsuario;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public TipoReceita getTipoReceitaSelecionada() {
		return tipoReceitaSelecionada;
	}

	public void setTipoReceitaSelecionada(TipoReceita tipoReceitaSelecionada) {
		this.tipoReceitaSelecionada = tipoReceitaSelecionada;
	}

	private TipoReceitaService getTipoReceitaService() {
		if (this.tipoReceitaService == null)
			this.tipoReceitaService = new TipoReceitaServiceImpl();
		return tipoReceitaService;
	}
}
