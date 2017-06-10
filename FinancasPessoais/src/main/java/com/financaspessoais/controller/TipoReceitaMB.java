package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	public void prepararCadastro() {
		if (this.tipoReceita == null)
			this.tipoReceita = new TipoReceita();		
	}
	
	public String cadastrarTipoReceita() {
		String retorno;
		boolean inseridoComSucesso = getTipoReceitaService().criarOuAtualizar(tipoReceita);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-tipo-receita?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de Receita não cadastrada!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			getTipoReceitaService().remover(this.tipoReceitaSelecionada);
			this.getTiposReceitasDoUsuario();
			context.addMessage(null, new FacesMessage("Tipo de Receita excluído com sucesso!"));
		} 
		catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	// Getters e Setters
	public List<TipoReceita> getTiposReceitasDoUsuario() {
		return getTipoReceitaService().listarPorUsuario();
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
