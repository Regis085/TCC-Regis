package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.TipoDespesa;

public interface TipoDespesaService {
	boolean criarOuAtualizar(TipoDespesa tipoDespesa);

	TipoDespesa buscar(Short id);

	List<TipoDespesa> listarPorUsuario();

	void remover(TipoDespesa tipoDespesa);
}
