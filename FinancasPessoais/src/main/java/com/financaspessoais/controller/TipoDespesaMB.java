package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	public void prepararCadastro() {
		if (this.tipoDespesa == null)
			this.tipoDespesa = new TipoDespesa();		
	}
	
	public String cadastrarTipoDespesa() {
		String retorno;
		boolean inseridoComSucesso = getTipoDespesaService().criarOuAtualizar(tipoDespesa);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-tipo-despesa?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de Despesa não cadastrada!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			getTipoDespesaService().remover(this.tipoDespesaSelecionada);
			this.getTiposDespesaDoUsuario();
			context.addMessage(null, new FacesMessage("Tipo de Despesa excluído com sucesso!"));
		} 
		catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	// Getters e Setters
	public List<TipoDespesa> getTiposDespesaDoUsuario() {
		return getTipoDespesaService().listarPorUsuario();
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
