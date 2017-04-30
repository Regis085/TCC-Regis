package service.impl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dominio.Perfil;
import dominio.Usuario;
import service.UsuarioService;
import utils.SessionContext;

public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static final Short ID_PERFIL_PADRAO = new Short("2");

	@Override
	public Usuario getUsuario(String login, String senha) {
		Usuario u = usuarioDAO.getUsuario(login, senha);
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
			novoUsuario = usuarioDAO.inserirUsuario(usuario);
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
		return usuarioDAO.deletarUsuario(usuario);
	}

}
