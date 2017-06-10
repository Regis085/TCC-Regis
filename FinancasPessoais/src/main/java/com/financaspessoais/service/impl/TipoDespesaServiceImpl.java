package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.TipoDespesaDAO;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.util.SessionContext;

public class TipoDespesaServiceImpl implements TipoDespesaService, Serializable {
	private static final long serialVersionUID = 1L;

	private TipoDespesaDAO tipoDespesaDAO;
	
	@Override
	public boolean criarOuAtualizar(TipoDespesa tipoDespesa) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		tipoDespesa.setProprietario(u);

		boolean retorno;
		TipoDespesa tipoDespesaBD = null;

		tipoDespesaBD = getTipoDespesaDAO().criarOuAtualizar(tipoDespesa);
		if (tipoDespesaBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(TipoDespesa tipoDespesa) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoDespesa> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<TipoDespesa> listaTipoDespesa = this.getTipoDespesaDAO().listarPorProprietario(u.getId());
		return listaTipoDespesa;
	}

	@Override
	public TipoDespesa buscar(Short id) {
		return this.getTipoDespesaDAO().buscarPorId(id);
	}
	
	private TipoDespesaDAO getTipoDespesaDAO() {
		if (this.tipoDespesaDAO == null)
			this.tipoDespesaDAO = new TipoDespesaDAO();
		return tipoDespesaDAO;
	}
}
