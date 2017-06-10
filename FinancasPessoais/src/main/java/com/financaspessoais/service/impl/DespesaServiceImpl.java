package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.DespesaDAO;
import com.financaspessoais.model.Despesa;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.DespesaService;
import com.financaspessoais.util.SessionContext;

public class DespesaServiceImpl implements DespesaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private DespesaDAO despesaDAO;

	@Override
	public boolean criarOuAtualizar(Despesa despesa) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		despesa.setProprietario(u);

		boolean retorno;
		Despesa despesaBD = null;

		despesaBD = this.getDespesaDAO().criarOuAtualizar(despesa);
		if (despesaBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(Despesa despesa) {
		this.getDespesaDAO().remover(despesa.getId());
	}

	@Override
	public List<Despesa> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Despesa> listaDespesa = this.getDespesaDAO().listarPorProprietario(u.getId());
		return listaDespesa;
	}

	@Override
	public Despesa buscar(Long id) {
		return this.getDespesaDAO().buscarPorId(id);
	}
	
	private DespesaDAO getDespesaDAO() {
		if (this.despesaDAO == null)
			this.despesaDAO = new DespesaDAO();
		return despesaDAO;
	}
}
