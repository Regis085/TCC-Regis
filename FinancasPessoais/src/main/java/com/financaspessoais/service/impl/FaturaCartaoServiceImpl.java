package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.FaturaCartaoDAO;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.util.SessionContext;

public class FaturaCartaoServiceImpl implements FaturaCartaoService, Serializable {
	private static final long serialVersionUID = 1L;

	private FaturaCartaoDAO faturaCartaoDAO;
	
	@Override
	public boolean criarOuAtualizar(FaturaCartao faturaCartao) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		faturaCartao.setProprietario(u);

		boolean retorno;
		FaturaCartao faturaCartaoBD = null;

		faturaCartaoBD = this.getFaturaCartaoDAO().criarOuAtualizar(faturaCartao);
		if (faturaCartaoBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(FaturaCartao faturaCartao) {
		this.getFaturaCartaoDAO().remover(faturaCartao.getId());
	}

	@Override
	public List<FaturaCartao> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<FaturaCartao> listaFaturaCartao = this.getFaturaCartaoDAO().listarPorProprietario(u.getId());
		return listaFaturaCartao;
	}

	@Override
	public FaturaCartao buscar(Long id) {
		return this.getFaturaCartaoDAO().buscarPorId(id);
	}

	private FaturaCartaoDAO getFaturaCartaoDAO() {
		if (this.faturaCartaoDAO == null)
			this.faturaCartaoDAO = new FaturaCartaoDAO();
		return faturaCartaoDAO;
	}
}
