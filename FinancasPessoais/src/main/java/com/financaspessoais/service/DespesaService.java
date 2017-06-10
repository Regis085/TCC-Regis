package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Despesa;

public interface DespesaService {
	boolean criarOuAtualizar(Despesa despesa);

	void remover(Despesa despesa);

	List<Despesa> listarPorUsuario();

	public Despesa buscar(Long id);
}
