package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.FaturaCartao;

public interface FaturaCartaoService {
	boolean criarOuAtualizar(FaturaCartao faturaCartao);

	void remover(FaturaCartao faturaCartao);

	List<FaturaCartao> listarPorUsuario();

	public FaturaCartao buscar(Long id);
}
