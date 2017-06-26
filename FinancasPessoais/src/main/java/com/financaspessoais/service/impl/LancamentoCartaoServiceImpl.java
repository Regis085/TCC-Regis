package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.financaspessoais.dao.LancamentoCartaoDAO;
import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.model.pk.LancamentoCartaoPK;
import com.financaspessoais.service.LancamentoCartaoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class LancamentoCartaoServiceImpl extends AbstractGenericService implements LancamentoCartaoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private LancamentoCartaoDAO lancamentoCartaoDAO;

	@Override
	public boolean criarOuAtualizar(LancamentoCartao lancamentoCartao) {
		
		this.limparListaMensagemErro();
		
		if (lancamentoCartao.getValor() == null || lancamentoCartao.getValor().intValue() == 0) {
			return false;
		}
		
		if (lancamentoCartao.getValor().compareTo(BigDecimal.ZERO) == -1)
			lancamentoCartao.setIsCredito(SimNao.SIM.getCodigo());
		else
			lancamentoCartao.setIsCredito(SimNao.NAO.getCodigo());
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		lancamentoCartao.setProprietario(u);
		
		boolean isCriacao = lancamentoCartao.getId() == null;
		
		validarCamposObrigatorios(lancamentoCartao);
		if (isCriacao) {
			validarDuplicidade(lancamentoCartao);
			if (naoOcorreramErros())
				configurarId(lancamentoCartao);
		}
		
		boolean retorno;
		
		if (naoOcorreramErros())
			this.getLancamentoCartaoDAO().criarOuAtualizar(lancamentoCartao);
		
		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			retorno = true;
		}
		return retorno;
	}
	
	private void validarDuplicidade(LancamentoCartao lancamentoCartao) {
		// TODO Auto-generated method stub
//		if (lancamentoCartao.getCartao() != null && lancamentoCartao.getAno() != null && lancamentoCartao.getMes() != null) {
//			FaturaCartao fatura = buscar(lancamentoCartao.getCartao().getCodigoCartaoDeCredito(), lancamentoCartao.getAno(), lancamentoCartao.getMes());
//			if (fatura != null) {
//				this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_FATURA_CARTAO);
//			}
//		}
	}

	private void configurarId(LancamentoCartao lancamentoCartao) {
		LancamentoCartaoPK id = new LancamentoCartaoPK();
		id.setCodigoCartaoDeCredito(lancamentoCartao.getCartao().getCodigoCartaoDeCredito());
		Long codigo = getLancamentoCartaoDAO().getNextId(id.getCodigoCartaoDeCredito());
		id.setCodigoLancamentoCartao(codigo);
		lancamentoCartao.setId(id);
	}
	
	private void validarCamposObrigatorios(LancamentoCartao lancamentoCartao) {
		// TODO implementar
//		if (lancamentoCartao.getCartao() == null)
//			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_CARTAO);
//		
//		if (lancamentoCartao.getAno() == null || lancamentoCartao.getAno().intValue() == 0 )
//			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_ANO_DA_FATURA);
//		else if (lancamentoCartao.getAno().intValue() < 2000)
//			this.adicionarMensagemErro(Constantes.MSG_VALOR_INVALIDO, Constantes.MSG_CAMPO_INVALIDO_ANO);
//		
//		if (lancamentoCartao.getMes() == null || lancamentoCartao.getMes().intValue() == 0) {
//			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_MES_DA_FATURA);
//		}
//		
//		if (lancamentoCartao.getDataVencimento() == null) {
//			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_DATA_VENCIMENTO);
//		}
//		
//		if (lancamentoCartao.getDataPagamento() != null && (lancamentoCartao.getValorPago() == null || lancamentoCartao.getValorPago().intValue() == 0)) {
//			this.adicionarMensagemErro(Constantes.MSG_VALORES_INCONSISTENTES, Constantes.MSG_VALIDACAO_VALOR_PAGO_NULO_DATA_PGTO_PREENCHIDA);
//		}
//		else if (lancamentoCartao.getDataPagamento() == null && (lancamentoCartao.getValorPago() != null && lancamentoCartao.getValorPago().intValue() != 0)) {
//			this.adicionarMensagemErro(Constantes.MSG_VALORES_INCONSISTENTES, Constantes.MSG_VALIDACAO_DATA_PGTO_NULA_VALOR_PAGO_PREENCHIDO);
//		}
	}
	
	@Override
	public void remover(LancamentoCartao lancamentoCartao) {
		this.limparListaMensagemErro();
		try {
			this.getLancamentoCartaoDAO().remover(lancamentoCartao.getId());
		} 
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<LancamentoCartao> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<LancamentoCartao> listaLancamentoCartao = this.getLancamentoCartaoDAO().listarPorProprietario(u.getId());
		return listaLancamentoCartao;
	}

	@Override
	public LancamentoCartao buscar(Short codigoCartaoDeCredito, Long codigoLancamentoCartao) {
		LancamentoCartaoPK id = new LancamentoCartaoPK();
		id.setCodigoCartaoDeCredito(codigoCartaoDeCredito);
		id.setCodigoLancamentoCartao(codigoLancamentoCartao);
		return this.getLancamentoCartaoDAO().buscarPorId(id);
	}
	
	private LancamentoCartaoDAO getLancamentoCartaoDAO() {
		if (this.lancamentoCartaoDAO == null)
			this.lancamentoCartaoDAO = new LancamentoCartaoDAO();
		return lancamentoCartaoDAO;
	}

}
