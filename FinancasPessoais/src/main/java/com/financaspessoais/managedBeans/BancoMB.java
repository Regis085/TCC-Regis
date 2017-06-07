package com.financaspessoais.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.financaspessoais.model.Banco;
import com.financaspessoais.service.BancoService;
import com.financaspessoais.service.impl.BancoServiceImpl;

@ManagedBean
@ViewScoped
public class BancoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private BancoService bancoService = new BancoServiceImpl();
	private Banco banco;
	private Banco bancoSelecionado;

	public void prepararCadastro() {
		if (this.banco == null)
			this.banco = new Banco();
	}

	public String cadastrarBanco() {
		String retorno;
		boolean inseridoComSucesso = bancoService.criarOuAtualizarBanco(banco);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-banco?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Banco não cadastrado!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			bancoService.excluirBanco(this.bancoSelecionado);
			this.getBancosDoUsuario();
			context.addMessage(null, new FacesMessage("Banco excluído com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	// Getters e Setters

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Banco getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}
	
	public List<Banco> getBancosDoUsuario() {
		return bancoService.listarPorUsuario();
	}
}
