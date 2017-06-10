package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.TipoReceitaDAO;
import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.util.SessionContext;

public class TipoReceitaServiceImpl implements TipoReceitaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private TipoReceitaDAO tipoReceitaDAO;

	@Override
	public boolean criarOuAtualizar(TipoReceita tipoReceita) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		tipoReceita.setProprietario(u);

		boolean retorno;
		TipoReceita tipoReceitaBD = null;

		tipoReceitaBD = this.getTipoReceitaDAO().criarOuAtualizar(tipoReceita);
		if (tipoReceitaBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
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
