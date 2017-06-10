package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Estabelecimento;

public interface EstabelecimentoService {
	boolean criarOuAtualizar(Estabelecimento estabelecimento);

	void remover(Estabelecimento estabelecimento);

	List<Estabelecimento> listarPorUsuario();

	public Estabelecimento buscar(Long id);
}
