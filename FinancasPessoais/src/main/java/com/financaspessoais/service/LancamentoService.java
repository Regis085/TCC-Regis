package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Lancamento;

public interface LancamentoService {
	boolean criarOuAtualizar(Lancamento receita);

	Lancamento buscar(Long id);
	
	List<Lancamento> listarPorUsuario();
	
	void remover(Lancamento receita);
}
