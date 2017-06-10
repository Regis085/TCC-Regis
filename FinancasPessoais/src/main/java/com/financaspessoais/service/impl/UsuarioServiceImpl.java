package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import com.financaspessoais.dao.UsuarioDAO;
import com.financaspessoais.model.Perfil;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class UsuarioServiceImpl implements UsuarioService, Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	private static final Short ID_PERFIL_PADRAO = new Short("2");
	private List<FacesMessage> listaMensagemErro;
	
	@Override
	public boolean criar(Usuario usuario) {
		boolean retorno;
		Usuario novoUsuario = null;
		limparListaMensagemErro();
		
		validarExistenciaUsuario(usuario);
		validarCamposObrigatorios(usuario);

		if (usuario.getPerfil() == null) {
			Perfil p = new Perfil();
			p.setId(ID_PERFIL_PADRAO);
			usuario.setPerfil(p);
		}

		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
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

	@Override
	public boolean excluir(Usuario usuario) {
		return getUsuarioDAO().remover(usuario.getId());
	}
	
	@Override
	public Usuario buscar(Short id) {
		return this.getUsuarioDAO().buscarPorId(id);
	}
	
	private void validarExistenciaUsuario(Usuario usuario) {
		Usuario usuarioBD = getUsuarioDAO().buscarPorLogin(usuario);
		if (usuarioBD != null) {
			this.adicionarMensagemErro("Já existe um usuário com este login.", "Escolha um outro login.");
		}
	}
	
	private void validarCamposObrigatorios(Usuario usuario) {
		
		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty())
			this.adicionarMensagemErro("Campo obrigatório não preenchido.", "Preencha o Login.");

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty())
			this.adicionarMensagemErro("Campo obrigatório não preenchido.", "Preencha a Senha.");

		if (usuario.getSobrenome() == null || usuario.getSobrenome().trim().isEmpty())
			this.adicionarMensagemErro("Campo obrigatório não preenchido.", "Preencha o Sobrenome.");

		if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty())
			this.adicionarMensagemErro("Campo obrigatório não preenchido.", "Preencha o CPF");
	}

	private UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null)
			this.usuarioDAO = new UsuarioDAO();
		return this.usuarioDAO;
	}
	
	private void adicionarMensagemErro(String msg1, String msg2) {
		FacesMessage mensagem = FacesContextUtil.criarMensagemErro(msg1, msg2);
		this.getListaMensagemErro().add(mensagem);
	}
	
	public List<FacesMessage> getListaMensagemErro() {
		if (this.listaMensagemErro == null) {
			this.listaMensagemErro = new ArrayList<FacesMessage>();
		}
		return this.listaMensagemErro;
	}

	public void limparListaMensagemErro() {
		this.listaMensagemErro = new ArrayList<FacesMessage>();
	}
}
