package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ContaDAO;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class ContaServiceImpl extends AbstractGenericService implements ContaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ContaDAO contaDAO;

	@Override
	public boolean criarOuAtualizar(Conta conta) {
		this.limparListaMensagemErro();

		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		conta.setProprietario(u);
		conta.setTipoConta(TipoConta.OUTRO);
		
		validarCamposObrigatorios(conta);
		validarDuplicidade(conta);
		
		if (this.getListaMensagemErro().isEmpty())
			this.getContaDAO().criarOuAtualizar(conta);

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
	public List<Conta> listarContas() {
		return getContaDAO().listar();
	}

	@Override
	public List<Conta> listarNaoBancariasPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Conta> listaConta = getContaDAO().listarNaoBancariasPorProprietario(u.getId());
		return listaConta;
	}
	
	@Override
	public List<Conta> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Conta> listaConta = getContaDAO().listarPorProprietario(u.getId());
		return listaConta;
	}

	@Override
	public void remover(Conta conta) {
		try {
			this.getContaDAO().remover(conta.getId());
			FacesContextUtil.adicionarMensagemDeInfo(Constantes.MSG_EXCLUSAO_BEM_SUCEDIDA);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public Conta buscar(Integer id) {
		return getContaDAO().buscarPorId(id);
	}
	
	private ContaDAO getContaDAO() {
		if (this.contaDAO == null)
			this.contaDAO = new ContaDAO();
		return this.contaDAO;
	}
	
	private void validarDuplicidade(Conta conta) {
		boolean isValido = true;
		try {
			isValido = this.getContaDAO().validarDuplicidade(conta);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_CARTAO_DE_CREDITO);
	}
	
	private void validarCamposObrigatorios(Conta conta) {
		if (conta.getNome() == null || conta.getNome().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NOME);
	}
}
