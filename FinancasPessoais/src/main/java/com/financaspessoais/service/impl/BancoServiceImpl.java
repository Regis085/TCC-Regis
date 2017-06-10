package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.BancoDAO;
import com.financaspessoais.model.Banco;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.BancoService;
import com.financaspessoais.util.SessionContext;

public class BancoServiceImpl implements BancoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private BancoDAO bancoDAO;

	@Override
	public boolean criarOuAtualizar(Banco banco) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		banco.setProprietario(u);

		boolean retorno;
		Banco novaBanco = null;

		novaBanco = getBancoDAO().criarOuAtualizar(banco);
		if (novaBanco != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(Banco banco) {
		this.getBancoDAO().remover(banco.getId());
	}

	@Override
	public List<Banco> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Banco> listaBanco = this.getBancoDAO().listarPorProprietario(u.getId());
		return listaBanco;
	}

	@Override
	public Banco buscar(Integer id) {
		return this.getBancoDAO().buscarPorId(id);
	}
	
	private BancoDAO getBancoDAO() {
		if (this.bancoDAO == null)
			this.bancoDAO = new BancoDAO();
		return bancoDAO;
	}
}
