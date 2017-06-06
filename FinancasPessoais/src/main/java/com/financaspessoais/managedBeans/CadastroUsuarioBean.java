package com.financaspessoais.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.service.impl.UsuarioServiceImpl;

@ManagedBean
@SessionScoped
public class CadastroUsuarioBean {
	private UsuarioService usuarioService = new UsuarioServiceImpl();
	private Usuario usuario = new Usuario();

	public String cadastrarUsuario() {
		String retorno;
		boolean inseridoComSucesso = usuarioService.inserirUsuario(usuario);
		if (inseridoComSucesso) {
			retorno = "/pages/index?faces-redirect=true";
		} else {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não cadastrado!", "Erro no Cadastro!"));
			retorno = null;
		}
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

}
