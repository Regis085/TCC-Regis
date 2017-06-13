package com.financaspessoais.service.impl;

import java.io.Serializable;

import com.financaspessoais.dao.UsuarioDAO;
import com.financaspessoais.model.Perfil;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class UsuarioServiceImpl extends AbstractGenericService implements UsuarioService, Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	
	@Override
	public boolean criar(Usuario usuario) {
		limparListaMensagemErro();
		
		if (usuario.getPerfil() == null) {
			Perfil p = new Perfil();
			p.setId(Constantes.ID_PERFIL_PADRAO);
			usuario.setPerfil(p);
		}
		
		validarExistenciaUsuario(usuario);
		validarCamposObrigatorios(usuario);
		
		Usuario novoUsuario = null;
		
		if (this.getListaMensagemErro().isEmpty())
			novoUsuario = this.getUsuarioDAO().criar(usuario);

		boolean retorno;
		
		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			SessionContext.getInstance().setUsuarioLogado(novoUsuario);
			retorno = true;
		}

		return retorno;
	}
	
	@Override
	public Usuario buscarPorLoginESenha(String login, String senha) {
		Usuario usuarioBD = getUsuarioDAO().buscarPorLoginESenha(login, senha);
		if (usuarioBD == null)
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_USUARIO_INVALIDO);
		else
			SessionContext.getInstance().setUsuarioLogado(usuarioBD);
		return usuarioBD;
	}

	@Override
	public void remover(Usuario usuario) {
		this.limparListaMensagemErro();
		try {
			getUsuarioDAO().remover(usuario.getId());
			FacesContextUtil.adicionarMensagemDeInfo(Constantes.MSG_EXCLUSAO_BEM_SUCEDIDA);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}
	
	@Override
	public Usuario buscar(Short id) {
		return this.getUsuarioDAO().buscarPorId(id);
	}
	
	private void validarExistenciaUsuario(Usuario usuario) {
		Usuario usuarioBD = getUsuarioDAO().buscarPorLogin(usuario);
		if (usuarioBD != null) {
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_USUARIO_1, Constantes.MSG_DUPLICIDADE_USUARIO_2);
		}
	}
	
	private void validarCamposObrigatorios(Usuario usuario) {
		
		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_LOGIN);

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_SENHA);

		if (usuario.getSobrenome() == null || usuario.getSobrenome().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_SOBRENOME);

		if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_CPF);
	}

	private UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null)
			this.usuarioDAO = new UsuarioDAO();
		return this.usuarioDAO;
	}
}
