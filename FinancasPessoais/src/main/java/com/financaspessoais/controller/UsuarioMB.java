package com.financaspessoais.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.service.impl.UsuarioServiceImpl;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UsuarioService usuarioService = new UsuarioServiceImpl();
	private Usuario usuario = new Usuario();

	public String cadastrarUsuario() {
		String retorno = null;
		boolean inseridoComSucesso = this.getUsuarioService().criar(usuario);
		if (inseridoComSucesso)
			retorno = "/pages/index?faces-redirect=true";
		return retorno;
	}

	public String voltar() {
		return "/pages/login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	private UsuarioService getUsuarioService() {
		if (this.usuarioService == null)
			this.usuarioService = new UsuarioServiceImpl();
		return usuarioService;
	}

}
