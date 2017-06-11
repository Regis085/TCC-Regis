package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ReceitaDAO;
import com.financaspessoais.model.Receita;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ReceitaService;
import com.financaspessoais.util.SessionContext;

public class ReceitaServiceImpl extends AbstractGenericService implements ReceitaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ReceitaDAO receitaDAO;

	@Override
	public boolean criarOuAtualizar(Receita receita) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		receita.setProprietario(u);

		boolean retorno;
		Receita receitaBD = null;

		receitaBD = this.getReceitaDAO().criarOuAtualizar(receita);
		if (receitaBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(Receita receita) {
		try {
			this.getReceitaDAO().remover(receita.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Receita> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Receita> listaReceita = this.getReceitaDAO().listarPorProprietario(u.getId());
		return listaReceita;
	}

	@Override
	public Receita buscar(Long id) {
		return this.getReceitaDAO().buscarPorId(id);
	}
	
	private ReceitaDAO getReceitaDAO() {
		if (this.receitaDAO == null)
			this.receitaDAO = new ReceitaDAO();
		return receitaDAO;
	}

}
