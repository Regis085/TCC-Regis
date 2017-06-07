package com.financaspessoais.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.impl.ContaServiceImpl;

@ManagedBean
@ViewScoped
public class ContaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private ContaService contaService = new ContaServiceImpl();
	private Conta conta;
	private Conta contaSelecionada;

	public void prepararCadastro() {
		if (this.conta == null)
			this.conta = new Conta();
	}

	public String cadastrarConta() {
		String retorno;
		boolean inseridoComSucesso = contaService.criarOuAtualizarConta(conta);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-conta?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta não cadastrada!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			contaService.excluirConta(this.contaSelecionada);
			this.getContasDoUsuario();
			context.addMessage(null, new FacesMessage("Conta excluída com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	// Getters e Setters

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoConta[] getTiposConta() {
		return TipoConta.values();
	}

	public List<Conta> getContas() {
		return contaService.listarContas();
	}

	public List<Conta> getContasDoUsuario() {
		return contaService.listarContasPorUsuario();
	}
}
