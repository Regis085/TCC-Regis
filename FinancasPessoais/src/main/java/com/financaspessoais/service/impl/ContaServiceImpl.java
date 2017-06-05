package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ContaDAO;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.util.SessionContext;

public class ContaServiceImpl implements ContaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ContaDAO contaDAO = new ContaDAO();

	@Override
	public boolean inserirConta(Conta conta) {

		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		conta.setProprietario(u);

		boolean retorno;
//		FacesMessage mensagem = null;
		Conta novaConta = null;

		novaConta = contaDAO.criarOuAtualizarConta(conta);
		if (novaConta != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public List<Conta> listarContas() {
		return this.contaDAO.listarContas();
	}

	@Override
	public List<Conta> listarContasPorUsuario() {
		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		List<Conta> listaConta = this.contaDAO.listarContasPorUsuario(u.getId());
		return listaConta;
	}

	@Override
	public void excluirConta(Conta conta) {
		this.contaDAO.excluirConta(conta);
	}

	@Override
	public Conta buscarConta(Integer id) {
		return this.contaDAO.buscarConta(id);
	}
}
