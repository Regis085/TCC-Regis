package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.ItemLancamentoCartao;

public interface ItemLancamentoCartaoService {
	boolean criarOuAtualizar(ItemLancamentoCartao item);

	void remover(ItemLancamentoCartao item);

	List<ItemLancamentoCartao> listarPorUsuario();

	public ItemLancamentoCartao buscar(Long id);
}
