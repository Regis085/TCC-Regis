package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.financaspessoais.dao.LancamentoDAO;
import com.financaspessoais.model.Lancamento;
import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.StatusLancamento;
import com.financaspessoais.model.TipoLancamento;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.LancamentoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;
import com.financaspessoais.util.Util;

public class LancamentoServiceImpl extends AbstractGenericService implements LancamentoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private LancamentoDAO lancamentoDAO;
	
	@Override
	public boolean criarOuAtualizar(Lancamento lancamento) {
		limparListaMensagemErro();
		
		configurarCampos(lancamento);
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		lancamento.setProprietario(u);
		
		validarCamposObrigatorios(lancamento);

		boolean retorno;
				
		if (this.getListaMensagemErro().isEmpty())
			this.getLancamentoDAO().criarOuAtualizar(lancamento);
		
		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			retorno = true;
		}

		return retorno;
	}

	private void configurarCampos(Lancamento lancamento) {
		if (lancamento.getTipoLancamento() != null && lancamento.getTipoLancamento().equals(TipoLancamento.RECEITA))
			lancamento.setEstabelecimento(null);
		
		if (lancamento.getIsTransferencia() == null) {
			lancamento.setIsTransferencia(SimNao.NAO.getCodigo());
		}
		
		if (lancamento.getDataRealizacao() == null)
			lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
		else
			lancamento.setStatusLancamento(StatusLancamento.REALIZADO);
	}

	@Override
	public void remover(Lancamento lancamento) {
		this.limparListaMensagemErro();
		try {
			this.getLancamentoDAO().remover(lancamento.getCodigoLancamento());
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<Lancamento> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Lancamento> listaLancamento = this.getLancamentoDAO().listarPorProprietario(u.getId());
		return listaLancamento;
	}

	@Override
	public Lancamento buscar(Long id) {
		return this.getLancamentoDAO().buscarPorId(id);
	}
	
	private LancamentoDAO getLancamentoDAO() {
		if (this.lancamentoDAO == null)
			this.lancamentoDAO = new LancamentoDAO();
		return lancamentoDAO;
	}
	
	private void validarCamposObrigatorios(Lancamento lancamento) {
		
		if (lancamento.getTipoLancamento() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_TIPO_LANCAMENTO);
		}
		
		if (lancamento.getDataRealizacao() != null) {
			if (lancamento.getConta() == null) {
				this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_CONTA);
			}
			if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) == 0) {
				this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
			} 
			if (lancamento.getDataRealizacao().after(Util.getDataAtualZeroHoras())) {
				this.adicionarMensagemErro(Constantes.MSG_VALOR_INVALIDO, Constantes.MSG_CAMPO_INVALIDO_DATA_REALIZACAO);
			}
		}
		
		if (lancamento.getValor() != null && lancamento.getValor().intValue() < 0) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_CAMPO_INVALIDO_VALOR_LANCAMENTO);
		}
		
		if (lancamento.getTipoLancamento() != null && (lancamento.getTipoLancamento().equals(TipoLancamento.RECEITA) && lancamento.getTipoDespesa() != null
				|| lancamento.getTipoLancamento().equals(TipoLancamento.DESPESA) && lancamento.getTipoReceita() != null) ) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA, Constantes.MSG_ERRO_TIPO_RECEITA_TIPO_DESPESA_MESMO_TEMPO);
		}
	}
}
