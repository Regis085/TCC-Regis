package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.LancamentoCartao;

public interface LancamentoCartaoService {
	boolean criarOuAtualizar(LancamentoCartao lancamentoCartao);

	void remover(LancamentoCartao lancamentoCartao);

	List<LancamentoCartao> listarPorUsuario();

	public LancamentoCartao buscar(Long id);
}
