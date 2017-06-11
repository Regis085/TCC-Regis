package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.ItemLancamentoCartaoDAO;
import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ItemLancamentoCartaoService;
import com.financaspessoais.util.SessionContext;

public class ItemLancamentoCartaoServiceImpl extends AbstractGenericService implements ItemLancamentoCartaoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ItemLancamentoCartaoDAO itemLancamentoCartaoDAO;

	@Override
	public boolean criarOuAtualizar(ItemLancamentoCartao item) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		item.setProprietario(u);

		boolean retorno;
		ItemLancamentoCartao itemBD = null;

		itemBD = this.getItemLancamentoCartaoDAO().criarOuAtualizar(item);
		if (itemBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
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
	public ItemLancamentoCartao buscar(Long id) {
		return this.getItemLancamentoCartaoDAO().buscarPorId(id);
	}
	
	private ItemLancamentoCartaoDAO getItemLancamentoCartaoDAO() {
		if (this.itemLancamentoCartaoDAO == null)
			this.itemLancamentoCartaoDAO = new ItemLancamentoCartaoDAO();
		return itemLancamentoCartaoDAO;
	}
}
