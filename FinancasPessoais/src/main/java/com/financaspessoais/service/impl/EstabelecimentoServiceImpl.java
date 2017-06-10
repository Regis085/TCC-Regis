package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.EstabelecimentoDAO;
import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.util.SessionContext;

public class EstabelecimentoServiceImpl implements EstabelecimentoService, Serializable {
	private static final long serialVersionUID = 1L;

	private EstabelecimentoDAO estabelecimentoDAO;
	
	@Override
	public boolean criarOuAtualizar(Estabelecimento estabelecimento) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		estabelecimento.setProprietario(u);

		boolean retorno;
		Estabelecimento estabelecimentoBD = null;

		estabelecimentoBD = this.getEstabelecimentoDAO().criarOuAtualizar(estabelecimento);
		if (estabelecimentoBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(Estabelecimento estabelecimento) {
		this.getEstabelecimentoDAO().remover(estabelecimento.getId());
	}

	@Override
	public List<Estabelecimento> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Estabelecimento> listaEstabelecimento = this.getEstabelecimentoDAO().listarPorProprietario(u.getId());
		return listaEstabelecimento;
	}

	@Override
	public Estabelecimento buscar(Long id) {
		return this.getEstabelecimentoDAO().buscarPorId(id);
	}

	private EstabelecimentoDAO getEstabelecimentoDAO() {
		if (this.estabelecimentoDAO == null)
			this.estabelecimentoDAO = new EstabelecimentoDAO();
		return estabelecimentoDAO;
	}
}
