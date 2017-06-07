package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Banco;

public interface BancoService {

	boolean criarOuAtualizarBanco(Banco banco);

	void excluirBanco(Banco bancoSelecionado);

	List<Banco> listarPorUsuario();
	
	public Banco buscarBanco(Integer id);
}
