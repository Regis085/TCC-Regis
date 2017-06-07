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
	public boolean criarOuAtualizarBanco(Banco banco) {
		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
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
	public void excluirBanco(Banco banco) {
		this.getBancoDAO().excluir(banco);
	}

	@Override
	public List<Banco> listarPorUsuario() {
		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		List<Banco> listaBanco = this.getBancoDAO().listarPorProprietario(u.getId());
		return listaBanco;
	}

	@Override
	public Banco buscarBanco(Integer id) {
		return this.getBancoDAO().buscarPorId(id);
	}
	
	private BancoDAO getBancoDAO() {
		if (this.bancoDAO == null)
			this.bancoDAO = new BancoDAO();
		return bancoDAO;
	}
}
