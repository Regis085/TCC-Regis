package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ContaBancariaDAO;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaBancariaService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class ContaBancariaServiceImpl extends AbstractGenericService implements ContaBancariaService, Serializable {
	private static final long serialVersionUID = 1L;

	private ContaBancariaDAO contaBancariaDAO;

	@Override
	public boolean criarOuAtualizar(ContaBancaria contaBancaria) {
		this.limparListaMensagemErro();

		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		contaBancaria.setProprietario(u);
		contaBancaria.setTipoConta(TipoConta.BANCARIA);
		
		validarCamposObrigatorios(contaBancaria);
		validarDuplicidade(contaBancaria);

		if (this.getListaMensagemErro().isEmpty())
			this.getContaBancariaDAO().criarOuAtualizar(contaBancaria);

		boolean retorno;
		
		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			retorno = true;
		}

		return retorno;
	}

	@Override
	public List<ContaBancaria> listarContasBancarias() {
		return getContaBancariaDAO().listar();
	}

	@Override
	public List<ContaBancaria> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<ContaBancaria> listaConta = getContaBancariaDAO().listarPorProprietario(u.getId());
		return listaConta;
	}

	@Override
	public void remover(ContaBancaria contaBancaria) {
		this.limparListaMensagemErro();
		try {
			this.getContaBancariaDAO().remover(contaBancaria.getId());
			FacesContextUtil.adicionarMensagemDeInfo(Constantes.MSG_EXCLUSAO_BEM_SUCEDIDA);
		} 
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public ContaBancaria buscar(Integer id) {
		return getContaBancariaDAO().buscarPorId(id);
	}

	private ContaBancariaDAO getContaBancariaDAO() {
		if (this.contaBancariaDAO == null)
			this.contaBancariaDAO = new ContaBancariaDAO();
		return this.contaBancariaDAO;
	}
	
	private void validarDuplicidade(ContaBancaria contaBancaria) {
		boolean isValido = true;
		try {
			isValido = this.getContaBancariaDAO().validarDuplicidade(contaBancaria);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_CARTAO_DE_CREDITO);
	}
	
	private void validarCamposObrigatorios(ContaBancaria contaBancaria) {
		if (contaBancaria.getNome() == null || contaBancaria.getNome().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NOME);
	}
}
