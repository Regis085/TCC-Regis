package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.TipoReceita;

public interface TipoReceitaService {
	boolean criarOuAtualizar(TipoReceita tipoReceita);

	TipoReceita buscar(Short id);

	List<TipoReceita> listarPorUsuario();

	void remover(TipoReceita tipoReceita);
}
