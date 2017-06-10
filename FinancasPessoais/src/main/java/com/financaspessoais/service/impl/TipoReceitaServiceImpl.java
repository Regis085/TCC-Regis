package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.financaspessoais.dao.TipoReceitaDAO;
import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.util.SessionContext;

public class TipoReceitaServiceImpl implements TipoReceitaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private TipoReceitaDAO tipoReceitaDAO;
	private FacesMessage mensagem;

	@Override
	public boolean criarOuAtualizar(TipoReceita tipoReceita) {
		mensagem = null;
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		tipoReceita.setProprietario(u);
		
		validarDuplicata(tipoReceita);
		validarCamposObrigatorios(tipoReceita);

		boolean retorno;
		TipoReceita tipoReceitaBD = null;
		
//		if (mensagem != null) {
//			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
//			FacesContext.getCurrentInstance().addMessage(null, mensagem);
//			retorno = false;
//		} 
//		else {
//			novoUsuario = getUsuarioDAO().criar(usuario);
//		}
		

		tipoReceitaBD = this.getTipoReceitaDAO().criarOuAtualizar(tipoReceita);
		if (tipoReceitaBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}
	
	private void validarDuplicata(TipoReceita tipoReceita) {
//		Usuario usuarioBD = getUsuarioDAO().buscarPorLogin(usuario);
//		if (usuarioBD != null) {
//			mensagem = new FacesMessage("Já existe um usuário com este login.", "Escolha um outro login.");
//		}
	}

	private void validarCamposObrigatorios(TipoReceita tipoReceita) {
		if (tipoReceita.getNome() == null || tipoReceita.getNome().trim().isEmpty())
			mensagem = new FacesMessage("Campo obrigatório não preenchido", "Preencha Nome");
	}

	@Override
	public void remover(TipoReceita tipoReceita) {
		this.getTipoReceitaDAO().remover(tipoReceita.getId());
	}

	@Override
	public List<TipoReceita> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<TipoReceita> listaTipoReceita = this.getTipoReceitaDAO().listarPorProprietario(u.getId());
		return listaTipoReceita;
	}

	@Override
	public TipoReceita buscar(Short id) {
		return this.getTipoReceitaDAO().buscarPorId(id);
	}
	
	private TipoReceitaDAO getTipoReceitaDAO() {
		if (this.tipoReceitaDAO == null)
			this.tipoReceitaDAO = new TipoReceitaDAO();
		return tipoReceitaDAO;
	}
}
