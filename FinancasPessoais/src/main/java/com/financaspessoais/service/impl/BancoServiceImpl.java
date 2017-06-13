package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.BancoDAO;
import com.financaspessoais.model.Banco;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.BancoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class BancoServiceImpl extends AbstractGenericService implements BancoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private BancoDAO bancoDAO;

	@Override
	public boolean criarOuAtualizar(Banco banco) {
		limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		banco.setProprietario(u);
		
		validarCamposObrigatorios(banco);
		validarDuplicidade(banco);

		boolean retorno;
		
		if (this.getListaMensagemErro().isEmpty())
			this.getBancoDAO().criarOuAtualizar(banco);
		
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
	public void remover(Banco banco) {
		this.limparListaMensagemErro();
		try {
			this.getBancoDAO().remover(banco.getId());
		} 
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<Banco> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Banco> listaBanco = this.getBancoDAO().listarPorProprietario(u.getId());
		return listaBanco;
	}

	@Override
	public Banco buscar(Short id) {
		return this.getBancoDAO().buscarPorId(id);
	}
	
	private BancoDAO getBancoDAO() {
		if (this.bancoDAO == null)
			this.bancoDAO = new BancoDAO();
		return bancoDAO;
	}
	
	private void validarDuplicidade(Banco banco) {
		boolean isValido = true;
		try {
			isValido = this.getBancoDAO().validarDuplicidade(banco);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_BANCO);
	}
	
	private void validarCamposObrigatorios(Banco banco) {
		if (banco.getNome() == null || banco.getNome().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NOME);
	}
}
