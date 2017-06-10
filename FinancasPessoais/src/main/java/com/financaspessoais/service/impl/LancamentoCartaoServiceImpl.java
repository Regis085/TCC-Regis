package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.LancamentoCartaoDAO;
import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.LancamentoCartaoService;
import com.financaspessoais.util.SessionContext;

public class LancamentoCartaoServiceImpl implements LancamentoCartaoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private LancamentoCartaoDAO lancamentoCartaoDAO;

	@Override
	public boolean criarOuAtualizar(LancamentoCartao lancamentoCartao) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		lancamentoCartao.setProprietario(u);

		boolean retorno;
		LancamentoCartao lancamentoCartaoBD = null;

		lancamentoCartaoBD = this.getLancamentoCartaoDAO().criarOuAtualizar(lancamentoCartao);
		if (lancamentoCartaoBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(LancamentoCartao lancamentoCartao) {
		this.getLancamentoCartaoDAO().remover(lancamentoCartao.getId());
	}

	@Override
	public List<LancamentoCartao> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<LancamentoCartao> listaLancamentoCartao = this.getLancamentoCartaoDAO().listarPorProprietario(u.getId());
		return listaLancamentoCartao;
	}

	@Override
	public LancamentoCartao buscar(Long id) {
		return this.getLancamentoCartaoDAO().buscarPorId(id);
	}
	
	private LancamentoCartaoDAO getLancamentoCartaoDAO() {
		if (this.lancamentoCartaoDAO == null)
			this.lancamentoCartaoDAO = new LancamentoCartaoDAO();
		return lancamentoCartaoDAO;
	}

}
