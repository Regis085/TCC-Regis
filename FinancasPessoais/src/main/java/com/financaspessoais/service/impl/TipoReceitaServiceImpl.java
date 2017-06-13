package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.TipoReceitaDAO;
import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class TipoReceitaServiceImpl extends AbstractGenericService implements TipoReceitaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private TipoReceitaDAO tipoReceitaDAO;

	@Override
	public boolean criarOuAtualizar(TipoReceita tipoReceita) {
		limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		tipoReceita.setProprietario(u);
		
		validarCamposObrigatorios(tipoReceita);
		validarDuplicidade(tipoReceita);

		boolean retorno;
		
		if (this.getListaMensagemErro().isEmpty())
			this.getTipoReceitaDAO().criarOuAtualizar(tipoReceita);
		
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
	public void remover(TipoReceita tipoReceita) {
		this.limparListaMensagemErro();
		try {
			this.getTipoReceitaDAO().remover(tipoReceita.getId());
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<TipoReceita> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<TipoReceita> listaTipoReceita = this.getTipoReceitaDAO().listarPorProprietario(u.getId());
		return listaTipoReceita;
	}

	@Override
	public TipoReceita buscar(Short id) {
		return this.getTipoReceitaDAO().buscarPorId(id);
	}
	
	private TipoReceitaDAO getTipoReceitaDAO() {
		if (this.tipoReceitaDAO == null)
			this.tipoReceitaDAO = new TipoReceitaDAO();
		return tipoReceitaDAO;
	}
	
	private void validarDuplicidade(TipoReceita tipoReceita) {
		boolean isValido = true;
		try {
			isValido = this.getTipoReceitaDAO().validarDuplicidade(tipoReceita);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_TIPO_RECEITA);
	}

	private void validarCamposObrigatorios(TipoReceita tipoReceita) {
		if (tipoReceita.getNome() == null || tipoReceita.getNome().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NOME);
	}
}
