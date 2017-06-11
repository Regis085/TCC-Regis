package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.EstabelecimentoDAO;
import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class EstabelecimentoServiceImpl extends AbstractGenericService implements EstabelecimentoService, Serializable {
	private static final long serialVersionUID = 1L;

	private EstabelecimentoDAO estabelecimentoDAO;
	
	@Override
	public boolean criarOuAtualizar(Estabelecimento estabelecimento) {
		limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		estabelecimento.setProprietario(u);
		
		validarCamposObrigatorios(estabelecimento);
		validarDuplicidade(estabelecimento);
		
		boolean retorno;

		if (this.getListaMensagemErro().isEmpty())
			this.getEstabelecimentoDAO().criarOuAtualizar(estabelecimento);
		
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
	public void remover(Estabelecimento estabelecimento) {
		this.limparListaMensagemErro();
		try {
			this.getEstabelecimentoDAO().remover(estabelecimento.getId());
			FacesContextUtil.adicionarMensagemDeInfo(Constantes.MSG_EXCLUSAO_BEM_SUCEDIDA);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<Estabelecimento> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Estabelecimento> listaEstabelecimento = this.getEstabelecimentoDAO().listarPorProprietario(u.getId());
		return listaEstabelecimento;
	}

	@Override
	public Estabelecimento buscar(Long id) {
		return this.getEstabelecimentoDAO().buscarPorId(id);
	}

	private EstabelecimentoDAO getEstabelecimentoDAO() {
		if (this.estabelecimentoDAO == null)
			this.estabelecimentoDAO = new EstabelecimentoDAO();
		return estabelecimentoDAO;
	}
	
	private void validarDuplicidade(Estabelecimento estabelecimento) {
		boolean isValido = true;
		try {
			isValido = this.getEstabelecimentoDAO().validarDuplicidade(estabelecimento);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_ESTABELECIMENTO);
	}
	
	private void validarCamposObrigatorios(Estabelecimento estabelecimento) {
		if (estabelecimento.getNome() == null || estabelecimento.getNome().trim().isEmpty())
			this.adicionarMensagemErro("Campo obrigatório não preenchido", "Preencha Nome");
	}
}
