package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.service.impl.TipoDespesaServiceImpl;

@ManagedBean
@ViewScoped
public class TipoDespesaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoDespesaService tipoDespesaService;
	private TipoDespesa tipoDespesa;
	private TipoDespesa tipoDespesaSelecionada;
	private List<TipoDespesa> tiposDespesaDoUsuario;
	
	public void prepararCadastro() {
		if (this.tipoDespesa == null)
			this.tipoDespesa = new TipoDespesa();		
	}
	
	public void limparCamposPrevisao() {
		if (this.tipoDespesa.getRecorrente().equals(SimNao.N.toString())) {
			this.tipoDespesa.setValorPrevisto(null);
			this.tipoDespesa.setDiaPagamentoPrevisto(null);
		}
	}
	
	public String cadastrarTipoDespesa() {
		String retorno = null;
		boolean inseridoComSucesso = this.getTipoDespesaService().criarOuAtualizar(tipoDespesa);
		if (inseridoComSucesso)
			retorno = "/pages/lista-tipo-despesa?faces-redirect=true";
		return retorno;
	}
	
	public void excluir() {
		this.getTipoDespesaService().remover(this.tipoDespesaSelecionada);
		this.tiposDespesaDoUsuario = null;
		this.getTiposDespesaDoUsuario();
	}

	// Getters e Setters
	public List<TipoDespesa> getTiposDespesaDoUsuario() {
		if (tiposDespesaDoUsuario == null) {
			tiposDespesaDoUsuario = getTipoDespesaService().listarPorUsuario();
		}
		return tiposDespesaDoUsuario;
	}
	
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public TipoDespesa getTipoDespesaSelecionada() {
		return tipoDespesaSelecionada;
	}

	public void setTipoDespesaSelecionada(TipoDespesa tipoDespesaSelecionada) {
		this.tipoDespesaSelecionada = tipoDespesaSelecionada;
	}

	private TipoDespesaService getTipoDespesaService() {
		if (this.tipoDespesaService == null)
			this.tipoDespesaService = new TipoDespesaServiceImpl();
		return tipoDespesaService;
	}
}
