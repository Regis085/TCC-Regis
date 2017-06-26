package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ItemLancamentoCartaoDAO;
import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.model.pk.ItemLancamentoCartaoPK;
import com.financaspessoais.service.ItemLancamentoCartaoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.SessionContext;

public class ItemLancamentoCartaoServiceImpl extends AbstractGenericService implements ItemLancamentoCartaoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ItemLancamentoCartaoDAO itemLancamentoCartaoDAO;

	@Override
	public boolean criarOuAtualizar(ItemLancamentoCartao item) {
		this.limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		item.setProprietario(u);
		
		boolean isAtualizando = this.isAtualizando(item);
		
		validarCamposObrigatorios(item, isAtualizando);
		
		if (isAtualizando == false) {
			configurarId(item);
		}
		else {
		}

		boolean retorno;
		ItemLancamentoCartao itemBD = null;

		itemBD = this.getItemLancamentoCartaoDAO().criarOuAtualizar(item);
		if (itemBD != null)
			retorno = true;
		else
			retorno = false;

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

	private boolean isAtualizando(ItemLancamentoCartao item) {
		if (item.getId() != null && item.getId().getCodigoCartaoDeCredito() != null
				&& item.getId().getCodigoLancamentoCartao() != null
				&& item.getId().getCodigoItemLancamentoCartao() != null) {
			return true;
		}
		return false;
	}

	private void validarCamposObrigatorios(ItemLancamentoCartao itemLancamentoCartao, boolean isAtualizando) {
		
		if (itemLancamentoCartao.getLancamentoCartao() == null || itemLancamentoCartao.getLancamentoCartao() == null) {
			
		}
		
		
		if (itemLancamentoCartao.getIsAvulso() == null || itemLancamentoCartao.getIsAvulso().equals(SimNao.SIM) == false
				|| itemLancamentoCartao.getIsAvulso().equals(SimNao.NAO) == false) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_AVULSO);
		}
		
		if (itemLancamentoCartao.getNumeroParcela() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NUMERO_PARCELA);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (itemLancamentoCartao.getValor() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		// if (itemLancamentoCartao.getNome() == null || itemLancamentoCartao.getNome().trim().isEmpty())
		// 		this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NOME);
	}

	@Override
	public void remover(ItemLancamentoCartao item) {
		try {
			this.getItemLancamentoCartaoDAO().remover(item.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public ItemLancamentoCartao buscar(Short codigoCartaoDeCredito, Long codigoLancamentoCartao) {
		ItemLancamentoCartaoPK id = new ItemLancamentoCartaoPK();
		id.setCodigoCartaoDeCredito(codigoCartaoDeCredito);
		id.setCodigoLancamentoCartao(codigoLancamentoCartao);
		return this.getItemLancamentoCartaoDAO().buscarPorId(id);
	}
	
	private ItemLancamentoCartaoDAO getItemLancamentoCartaoDAO() {
		if (this.itemLancamentoCartaoDAO == null)
			this.itemLancamentoCartaoDAO = new ItemLancamentoCartaoDAO();
		return itemLancamentoCartaoDAO;
	}
}
