package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.LancamentoCartao;

public interface LancamentoCartaoService {
	boolean criarOuAtualizar(LancamentoCartao lancamentoCartao);
	
	LancamentoCartao buscar(Short codigoCartaoDeCredito, Long codigoLancamentoCartao);

	List<LancamentoCartao> listarPorUsuario();
	
	List<LancamentoCartao> listarPorUsuarioECartaoDeCredito(Short codigoCartaoDeCredito);
	
	void remover(LancamentoCartao lancamentoCartao);
}
