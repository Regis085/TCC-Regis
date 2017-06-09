package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ContaBancariaDAO;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaBancariaService;
import com.financaspessoais.util.SessionContext;

public class ContaBancariaServiceImpl implements ContaBancariaService, Serializable {

	private static final long serialVersionUID = 1L;

	private ContaBancariaDAO contaBancariaDAO;

	@Override
	public boolean criarOuAtualizarContaBancaria(ContaBancaria contaBancaria) {

		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		contaBancaria.setProprietario(u);
		
		contaBancaria.setTipoConta(TipoConta.BANCARIA);

		boolean retorno;
		Conta novaContaBancaria = null;

		novaContaBancaria = getContaBancariaDAO().criarOuAtualizar(contaBancaria);
		if (novaContaBancaria != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public List<ContaBancaria> listarContasBancarias() {
		return getContaBancariaDAO().listar();
	}

	@Override
	public List<ContaBancaria> listarContasBancariasPorUsuario() {
		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		List<ContaBancaria> listaConta = getContaBancariaDAO().listarPorProprietario(u.getId());
		return listaConta;
	}

	@Override
	public void excluirConta(ContaBancaria contaBancaria) {
		getContaBancariaDAO().excluir(contaBancaria);

	}

	private ContaBancariaDAO getContaBancariaDAO() {
		if (this.contaBancariaDAO == null)
			this.contaBancariaDAO = new ContaBancariaDAO();
		return this.contaBancariaDAO;
	}

	@Override
	public ContaBancaria buscarContaBancaria(Integer id) {
		return getContaBancariaDAO().buscarPorId(id);
	}
}
