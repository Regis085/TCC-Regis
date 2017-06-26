package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.FaturaCartaoDAO;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.StatusFaturaCartao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.model.pk.FaturaCartaoPK;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class FaturaCartaoServiceImpl extends AbstractGenericService implements FaturaCartaoService, Serializable {
	private static final long serialVersionUID = 1L;

	private FaturaCartaoDAO faturaCartaoDAO;
	
	@Override
	public boolean criarOuAtualizar(FaturaCartao faturaCartao) {
		limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		faturaCartao.setProprietario(u);
		
		if (faturaCartao.getStatusFaturaCartao() == null) {
			faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PENDENTE);
		}
		
		boolean isCriacao = faturaCartao.getId() == null;
		
		validarCamposObrigatorios(faturaCartao);
		if (isCriacao) {
			validarDuplicidade(faturaCartao);
			if (naoOcorreramErros())
				configurarId(faturaCartao);
		}

		boolean retorno;
		
		if (naoOcorreramErros())
			this.getFaturaCartaoDAO().criarOuAtualizar(faturaCartao);
		
		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			retorno = true;
		}
		return retorno;
	}

	private void validarCamposObrigatorios(FaturaCartao faturaCartao) {
		if (faturaCartao.getCartao() == null)
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_CARTAO);
		
		if (faturaCartao.getAno() == null || faturaCartao.getAno().intValue() == 0 )
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_ANO_DA_FATURA);
		else if (faturaCartao.getAno().intValue() < 2000)
			this.adicionarMensagemErro(Constantes.MSG_VALOR_INVALIDO, Constantes.MSG_CAMPO_INVALIDO_ANO);
		
		if (faturaCartao.getMes() == null || faturaCartao.getMes().intValue() == 0) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_MES_DA_FATURA);
		}
		
		if (faturaCartao.getDataVencimento() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_DATA_VENCIMENTO);
		}
		
		if (faturaCartao.getDataPagamento() != null && (faturaCartao.getValorPago() == null || faturaCartao.getValorPago().intValue() == 0)) {
			this.adicionarMensagemErro(Constantes.MSG_VALORES_INCONSISTENTES, Constantes.MSG_VALIDACAO_VALOR_PAGO_NULO_DATA_PGTO_PREENCHIDA);
		}
		else if (faturaCartao.getDataPagamento() == null && (faturaCartao.getValorPago() != null && faturaCartao.getValorPago().intValue() != 0)) {
			this.adicionarMensagemErro(Constantes.MSG_VALORES_INCONSISTENTES, Constantes.MSG_VALIDACAO_DATA_PGTO_NULA_VALOR_PAGO_PREENCHIDO);
		}
	}
	
	private void validarDuplicidade(FaturaCartao faturaCartao) {
		
		if (faturaCartao.getCartao() != null && faturaCartao.getAno() != null && faturaCartao.getMes() != null) {
			FaturaCartao fatura = buscar(faturaCartao.getCartao().getCodigoCartaoDeCredito(), faturaCartao.getAno(), faturaCartao.getMes());
			if (fatura != null) {
				this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_FATURA_CARTAO);
			}
		}
	}

	private void configurarId(FaturaCartao faturaCartao) {
		FaturaCartaoPK id = new FaturaCartaoPK();
		id.setCodigoCartaoDeCredito(faturaCartao.getCartao().getCodigoCartaoDeCredito());
		id.setAno(faturaCartao.getAno());
		id.setMes(faturaCartao.getMes());
		faturaCartao.setId(id);
	}

	@Override
	public void remover(FaturaCartao faturaCartao) {
		this.limparListaMensagemErro();
		try {
			this.getFaturaCartaoDAO().remover(faturaCartao.getId());
		} 
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<FaturaCartao> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<FaturaCartao> listaFaturaCartao = this.getFaturaCartaoDAO().listarPorProprietario(u.getId());
		return listaFaturaCartao;
	}

	@Override
	public FaturaCartao buscar(Short codigoCartaoDeCredito, Short ano, Short mes) {
		FaturaCartaoPK id = new FaturaCartaoPK();
		id.setCodigoCartaoDeCredito(codigoCartaoDeCredito);
		id.setAno(ano);
		id.setMes(mes);
		return this.getFaturaCartaoDAO().buscarPorId(id);
	}

	private FaturaCartaoDAO getFaturaCartaoDAO() {
		if (this.faturaCartaoDAO == null)
			this.faturaCartaoDAO = new FaturaCartaoDAO();
		return faturaCartaoDAO;
	}
}
