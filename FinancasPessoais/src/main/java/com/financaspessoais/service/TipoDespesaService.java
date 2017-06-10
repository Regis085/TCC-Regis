package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.TipoDespesa;

public interface TipoDespesaService {
	
	boolean criarOuAtualizar(TipoDespesa tipoDespesa);

	void remover(TipoDespesa tipoDespesa);

	List<TipoDespesa> listarPorUsuario();

	public TipoDespesa buscar(Short id);
}
