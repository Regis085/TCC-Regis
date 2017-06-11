package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ContaDAO;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.util.SessionContext;

public class ContaServiceImpl extends AbstractGenericService implements ContaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ContaDAO contaDAO;

	@Override
	public boolean criarOuAtualizar(Conta conta) {

		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		conta.setProprietario(u);
		
		conta.setTipoConta(TipoConta.OUTRO);

		boolean retorno;
		Conta novaConta = null;

		novaConta = getContaDAO().criarOuAtualizar(conta);
		if (novaConta != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public List<Conta> listarContas() {
		return getContaDAO().listar();
	}

	@Override
	public List<Conta> listarContasPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Conta> listaConta = getContaDAO().listarPorProprietario(u.getId());
		return listaConta;
	}

	@Override
	public void excluir(Conta conta) {
		try {
			getContaDAO().remover(conta.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Conta buscarConta(Integer id) {
		return getContaDAO().buscarPorId(id);
	}
	
	private ContaDAO getContaDAO() {
		if (this.contaDAO == null)
			this.contaDAO = new ContaDAO();
		return this.contaDAO;
	}
}
