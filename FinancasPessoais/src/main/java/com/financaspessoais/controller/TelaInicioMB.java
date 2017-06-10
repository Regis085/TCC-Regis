package com.financaspessoais.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.util.SessionContext;
import com.financaspessoais.util.Util;

@ManagedBean
@SessionScoped
public class TelaInicioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String login;
	private Short id;
	private String nomePerfil;
	private String cpf;

	public TelaInicioMB() {
		System.out.println("Entrou em TelaInicioMB");
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		if (u != null) {
			System.out.println("Nome do Usuário: " + u.getNome());
			
			this.nome = u.getNome();
			this.login = u.getLogin();
			this.id = u.getId();
			this.nomePerfil = u.getPerfil().getNome();
			this.cpf = u.getCpf();
		}
		else {
			System.out.println("Sessão Finalizada!");
		}
	}
	
	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "/pages/login?faces-redirect=true";
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public Short getId() {
		return id;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public String getCpf() {
		return cpf;
	}
}
