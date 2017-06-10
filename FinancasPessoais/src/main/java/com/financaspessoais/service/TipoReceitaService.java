package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.TipoReceita;

public interface TipoReceitaService {
	
	boolean criarOuAtualizar(TipoReceita tipoReceita);

	void remover(TipoReceita tipoReceita);

	List<TipoReceita> listarPorUsuario();

	public TipoReceita buscar(Short id);
}
