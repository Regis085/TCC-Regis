package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.ItemLancamentoCartao;

public interface ItemLancamentoCartaoService {
	boolean criarOuAtualizar(ItemLancamentoCartao item);

	ItemLancamentoCartao buscar(Short codigoCartaoDeCredito, Long codigoLancamentoCartao, Long codigoItemLancamentoCartao);

	List<ItemLancamentoCartao> listarPorUsuario();

	void remover(ItemLancamentoCartao item);
}
