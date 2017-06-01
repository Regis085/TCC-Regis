package com.financaspessoais.service.impl;

import java.util.List;

import com.financaspessoais.dao.ContaDAO;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.util.SessionContext;

public class ContaServiceImpl implements ContaService {

	private ContaDAO contaDAO = new ContaDAO();

	@Override
	public Conta getContaPeloId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserirConta(Conta conta) {

		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		conta.setProprietario(u);

		boolean retorno;
//		FacesMessage mensagem = null;
		Conta novaConta = null;

		novaConta = contaDAO.inserirConta(conta);
		if (novaConta != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public boolean deletarConta(Conta conta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Conta> listarContas() {
		return contaDAO.todas();
	}

	@Override
	public List<Conta> listarContasPorUsuario() {
		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		List<Conta> listaConta = contaDAO.listarContasPorUsuario(u.getId());
		return listaConta;
	}
}