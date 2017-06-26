package com.financaspessoais.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import com.financaspessoais.util.FacesContextUtil;

public abstract class AbstractGenericService {

	private List<FacesMessage> listaMensagemErro;
	
	protected boolean naoOcorreramErros() {
		return this.getListaMensagemErro().isEmpty();
	}
	
	protected void adicionarMensagemErro(String msg1) {
		FacesMessage mensagem = FacesContextUtil.criarMensagemErro(msg1);
		this.getListaMensagemErro().add(mensagem);
	}
	
	protected void adicionarMensagemErro(String msg1, String msg2) {
		FacesMessage mensagem = FacesContextUtil.criarMensagemErro(msg1, msg2);
		this.getListaMensagemErro().add(mensagem);
	}
	
	protected List<FacesMessage> getListaMensagemErro() {
		if (this.listaMensagemErro == null) {
			this.listaMensagemErro = new ArrayList<FacesMessage>();
		}
		return this.listaMensagemErro;
	}

	protected void limparListaMensagemErro() {
		this.listaMensagemErro = new ArrayList<FacesMessage>();
	}
}
