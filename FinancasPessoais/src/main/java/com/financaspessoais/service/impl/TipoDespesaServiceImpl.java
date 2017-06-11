package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.TipoDespesaDAO;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class TipoDespesaServiceImpl extends AbstractGenericService implements TipoDespesaService, Serializable {
	private static final long serialVersionUID = 1L;

	private TipoDespesaDAO tipoDespesaDAO;
	
	@Override
	public boolean criarOuAtualizar(TipoDespesa tipoDespesa) {
		limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		tipoDespesa.setProprietario(u);
		
		validarCamposObrigatorios(tipoDespesa);
		validarDuplicidade(tipoDespesa);

		boolean retorno;
				
		if (this.getListaMensagemErro().isEmpty())
			getTipoDespesaDAO().criarOuAtualizar(tipoDespesa);
		
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
	public void remover(TipoDespesa tipoDespesa) {
		this.limparListaMensagemErro();
		try {
			this.getTipoDespesaDAO().remover(tipoDespesa.getId());
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}

	}

	@Override
	public List<TipoDespesa> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<TipoDespesa> listaTipoDespesa = this.getTipoDespesaDAO().listarPorProprietario(u.getId());
		return listaTipoDespesa;
	}

	@Override
	public TipoDespesa buscar(Short id) {
		return this.getTipoDespesaDAO().buscarPorId(id);
	}
	
	private TipoDespesaDAO getTipoDespesaDAO() {
		if (this.tipoDespesaDAO == null)
			this.tipoDespesaDAO = new TipoDespesaDAO();
		return tipoDespesaDAO;
	}
	
	private void validarDuplicidade(TipoDespesa tipoDespesa) {
		boolean isValido = true;
		try {
			isValido = this.getTipoDespesaDAO().validarDuplicidade(tipoDespesa);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_TIPO_DESPESA);
	}
	
	private void validarCamposObrigatorios(TipoDespesa tipoDespesa) {
		if (tipoDespesa.getNome() == null || tipoDespesa.getNome().trim().isEmpty())
			this.adicionarMensagemErro("Campo obrigatório não preenchido", "Preencha Nome");
	}
}
