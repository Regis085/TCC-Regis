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
	private FacesMessage mensagem;

	@Override
	public boolean criar(Usuario usuario) {
		boolean retorno;
		Usuario novoUsuario = null;
		mensagem = null;
		
		validarExistenciaUsuario(usuario);
		validarCamposObrigatorios(usuario);

		if (usuario.getPerfil() == null) {
			Perfil p = new Perfil();
			p.setId(ID_PERFIL_PADRAO);
			usuario.setPerfil(p);
		}

		if (mensagem != null) {
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			retorno = false;
		} 
		else {
			novoUsuario = getUsuarioDAO().criar(usuario);
		}

		if (novoUsuario == null) {
			retorno = false;
		}
		else {
			retorno = true;
			SessionContext.getInstance().setUsuarioLogado(novoUsuario);
		}

		return retorno;
	}
	
	@Override
	public Usuario buscarPorLoginESenha(String login, String senha) {
		Usuario usuarioBD = getUsuarioDAO().buscarPorLoginESenha(login, senha);
		SessionContext.getInstance().setUsuarioLogado(usuarioBD);
		return usuarioBD;
	}
	
	private void validarExistenciaUsuario(Usuario usuario) {
		Usuario usuarioBD = getUsuarioDAO().buscarPorLogin(usuario);
		if (usuarioBD != null) {
			mensagem = new FacesMessage("Já existe um usuário com este login.", "Escolha um outro login.");
		}
	}

	private void validarCamposObrigatorios(Usuario usuario) {
		
		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty())
			mensagem = new FacesMessage("Campo obrigatório não preenchido", "Preencha Login");

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty())
			mensagem = new FacesMessage("Campo obrigatório não preenchido", "Preencha Senha");

		if (usuario.getSobrenome() == null || usuario.getSobrenome().trim().isEmpty())
			mensagem = new FacesMessage("Campo obrigatório não preenchido", "Preencha Sobrenome");

		if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty())
			mensagem = new FacesMessage("Campo obrigatório não preenchido", "Preencha CPF");
	}

	@Override
	public boolean excluir(Usuario usuario) {
		return getUsuarioDAO().remover(usuario.getId());
	}
	
	@Override
	public Usuario buscar(Short id) {
		return this.getUsuarioDAO().buscarPorId(id);
	}

	private UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null)
			this.usuarioDAO = new UsuarioDAO();
		return this.usuarioDAO;
	}
}
