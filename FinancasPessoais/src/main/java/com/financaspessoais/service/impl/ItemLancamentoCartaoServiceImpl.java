package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.financaspessoais.dao.ItemLancamentoCartaoDAO;
import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.StatusFaturaCartao;
import com.financaspessoais.model.StatusItemLancamentoCartao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.model.pk.ItemLancamentoCartaoPK;
import com.financaspessoais.service.ItemLancamentoCartaoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class ItemLancamentoCartaoServiceImpl extends AbstractGenericService implements ItemLancamentoCartaoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ItemLancamentoCartaoDAO itemLancamentoCartaoDAO;

	@Override
	public boolean criarOuAtualizar(ItemLancamentoCartao item) {
		
		limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		item.setProprietario(u);
		
		boolean isCriacao = item.getId() == null;
		
		validarCamposObrigatorios(item, isCriacao);
		validarConsistencia(item);
		
		if (item.getValor().compareTo(BigDecimal.ZERO) == -1)
			item.setIsCredito(SimNao.SIM.getCodigo());
		else
			item.setIsCredito(SimNao.NAO.getCodigo());
		
		if (isCriacao) {
			if (naoOcorreramErros()) {
				item.setAnoFaturaCartao(item.getFaturaCartao().getAno());
				item.setMesFaturaCartao(item.getFaturaCartao().getMes());
				configurarId(item);
				
				if (item.getStatus() == null)
					if (item.getFaturaCartao().getStatusFaturaCartao() == null || item.getFaturaCartao().getStatusFaturaCartao().equals(StatusFaturaCartao.PENDENTE) 
							|| item.getFaturaCartao().getStatusFaturaCartao().equals(StatusFaturaCartao.ATRASADO))
						item.setStatus(StatusItemLancamentoCartao.PREVISTO);
					else
						item.setStatus(StatusItemLancamentoCartao.REAL);
			}
		}

		boolean retorno;

		if (naoOcorreramErros())
			getItemLancamentoCartaoDAO().criarOuAtualizar(item);

		if (getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			retorno = true;
		}
		return retorno;
	}

	private void configurarId(ItemLancamentoCartao item) {
		ItemLancamentoCartaoPK id = new ItemLancamentoCartaoPK();
		id.setCodigoCartaoDeCredito(item.getLancamentoCartao().getId().getCodigoCartaoDeCredito());
		id.setCodigoLancamentoCartao(item.getLancamentoCartao().getId().getCodigoLancamentoCartao());
		Long codigo = getItemLancamentoCartaoDAO().getNextId(id.getCodigoCartaoDeCredito(), id.getCodigoLancamentoCartao());
		id.setCodigoItemLancamentoCartao(codigo);
		item.setId(id);
	}

	private void validarCamposObrigatorios(ItemLancamentoCartao itemLancamentoCartao, boolean isCriando) {
		
		if (itemLancamentoCartao.getFaturaCartao() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_FATURA_CARTAO);
		}
		
		if (itemLancamentoCartao.getLancamentoCartao() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_LANCAMENTO_CARTAO);
		}
		
		if (itemLancamentoCartao.getNumeroParcela() == null || itemLancamentoCartao.getNumeroParcela().intValue() == 0) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NUMERO_PARCELA);
		}
		
		if (itemLancamentoCartao.getValor() == null || itemLancamentoCartao.getValor().intValue() == 0) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getCartaoDeCredito() == null || itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_CARTAO);
		}
	}
	
	private void validarConsistencia(ItemLancamentoCartao itemLancamentoCartao) {
		
		if (naoOcorreramErros()) {
			boolean condicao1 = itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito().equals(itemLancamentoCartao.getLancamentoCartao().getId().getCodigoCartaoDeCredito());
			boolean condicao2 = itemLancamentoCartao.getCartaoDeCredito().getCodigoCartaoDeCredito().equals(itemLancamentoCartao.getFaturaCartao().getId().getCodigoCartaoDeCredito());
			
			if (!condicao1 || !condicao2) {
				this.adicionarMensagemErro(Constantes.MSG_VALORES_INCONSISTENTES, Constantes.MSG_VALIDACAO_FATURA_LANCAMENTO_MESMO_CARTAO);
			}
		}
	}

	@Override
	public void remover(ItemLancamentoCartao item) {
		this.limparListaMensagemErro();
		try {
			this.getItemLancamentoCartaoDAO().remover(item.getId());
		} 
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<ItemLancamentoCartao> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<ItemLancamentoCartao> listaItemLancamentoCartao = this.getItemLancamentoCartaoDAO().listarPorProprietario(u.getId());
		return listaItemLancamentoCartao;
	}

	@Override
	public ItemLancamentoCartao buscar(Short codigoCartaoDeCredito, Long codigoLancamentoCartao, Long codigoItemLancamentoCartao) {
		ItemLancamentoCartaoPK id = new ItemLancamentoCartaoPK();
		id.setCodigoCartaoDeCredito(codigoCartaoDeCredito);
		id.setCodigoLancamentoCartao(codigoLancamentoCartao);
		id.setCodigoItemLancamentoCartao(codigoItemLancamentoCartao);
		return this.getItemLancamentoCartaoDAO().buscarPorId(id);
	}
	
	private ItemLancamentoCartaoDAO getItemLancamentoCartaoDAO() {
		if (this.itemLancamentoCartaoDAO == null)
			this.itemLancamentoCartaoDAO = new ItemLancamentoCartaoDAO();
		return itemLancamentoCartaoDAO;
	}
}
