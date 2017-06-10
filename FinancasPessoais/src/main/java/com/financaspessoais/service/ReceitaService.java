package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Receita;

public interface ReceitaService {
	boolean criarOuAtualizar(Receita receita);

	void remover(Receita receita);

	List<Receita> listarPorUsuario();

	public Receita buscar(Long id);
}
