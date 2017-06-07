package com.financaspessoais.service.impl;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.financaspessoais.dao.UsuarioDAO;
import com.financaspessoais.model.Perfil;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.util.SessionContext;

public class UsuarioServiceImpl implements UsuarioService, Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	private static final Short ID_PERFIL_PADRAO = new Short("2");

	@Override
	public Usuario getUsuario(String login, String senha) {
		Usuario u = getUsuarioDAO().buscarPorLoginESenha(login, senha);
		SessionContext.getInstance().setAttribute("usuarioLogado", u);
		return u;
	}

	@Override
	public boolean inserirUsuario(Usuario usuario) {
		boolean retorno;
		FacesMessage mensagem = null;
		Usuario novoUsuario = null;

		if (usuario.getPerfil() == null) {
			Perfil p = new Perfil();
			p.setId(ID_PERFIL_PADRAO);
			usuario.setPerfil(p);
		}

		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			mensagem = new FacesMessage("Preencha Nome", "Preencha Nome");
		}

		if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
			mensagem = new FacesMessage("Preencha CPF", "Preencha CPF");
		}

		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
			mensagem = new FacesMessage("Preencha Login", "Preencha Login");
		}

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
			mensagem = new FacesMessage("Preencha Senha", "Preencha Senha");
		}

		if (mensagem != null) {
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			retorno = false;
		} else {
			novoUsuario = getUsuarioDAO().criar(usuario);
		}

		if (novoUsuario == null) {
			retorno = false;
		} else {
			retorno = true;
			SessionContext.getInstance().setAttribute("usuarioLogado", novoUsuario);
		}

		return retorno;
	}

	@Override
	public boolean deletarUsuario(Usuario usuario) {
		return getUsuarioDAO().excluir(usuario);
	}
	
	private UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null)
			this.usuarioDAO = new UsuarioDAO();
		return this.usuarioDAO;
	}
}
