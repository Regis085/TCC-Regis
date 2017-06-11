package com.financaspessoais.util;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesContextUtil {

	public static FacesMessage criarMensagemErro(String msg1, String msg2) {
		FacesMessage fm = null;
		if (msg1 != null && msg1.trim().isEmpty() == false && msg2 != null && msg2.trim().isEmpty() == false) {
			fm = new FacesMessage(msg1, msg2);
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		return fm;
	}
	
	public static FacesMessage criarMensagemErro(String msg) {
		FacesMessage fm = null;
		if (msg != null && msg.trim().isEmpty() == false) {
			fm = new FacesMessage(msg, "");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		return fm;
	}
	
	public static FacesMessage criarMensagemInfo(String msg) {
		FacesMessage fm = null;
		if (msg != null && msg.trim().isEmpty() == false) {
			fm = new FacesMessage(msg, "");
			fm.setSeverity(FacesMessage.SEVERITY_INFO);
		}
		return fm;
	}
	
	public static void adicionarMensagemDeErro(String msg1, String msg2) {
		FacesMessage mensagem;
		mensagem = new FacesMessage(msg1, msg2);
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public static void adicionarMensagemDeErro(String msg) {
		FacesMessage mensagem;
		mensagem = new FacesMessage(msg, "");
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public static void adicionarMensagemDeInfo(String msg) {
		FacesMessage mensagem;
		mensagem = new FacesMessage(msg, "");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public static void adicionarMensagemDeErro(FacesMessage mensagem) {
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public static void adicionarMensagensDeErro(List<FacesMessage> listaMensagemErro) {
		if (listaMensagemErro != null && listaMensagemErro.size() > 0) {
			for (FacesMessage mensagem : listaMensagemErro) {
				adicionarMensagemDeErro(mensagem);
			}
		}
	}
}
