package com.financaspessoais.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.service.impl.UsuarioServiceImpl;
import com.financaspessoais.util.SessionContext;
import com.financaspessoais.util.Util;

@ManagedBean
@SessionScoped
//@ApplicationScoped
public class LoginMB implements Serializable{

	private static final long serialVersionUID = 1L;
	private UsuarioService usuarioService = new UsuarioServiceImpl();
	private Usuario usuario = new Usuario();

	public String cadastrar() {
		return "/pages/cadastro-usuario?faces-redirect=true";
	}

	public String enviar() {

		SessionContext.getInstance().encerrarSessao();

		usuario = usuarioService.getUsuario(usuario.getLogin(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique seu login e senha.", "Tente novamente."));
			return null;
		} else {
			return "/pages/index?faces-redirect=true";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "/pages/login?faces-redirect=true";
	}
}
