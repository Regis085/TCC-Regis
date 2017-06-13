package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Receita;

public interface ReceitaService {
	boolean criarOuAtualizar(Receita receita);

	Receita buscar(Long id);
	
	List<Receita> listarPorUsuario();
	
	void remover(Receita receita);
}
