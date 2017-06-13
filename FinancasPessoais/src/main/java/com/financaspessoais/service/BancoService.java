package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Banco;

public interface BancoService {

	boolean criarOuAtualizar(Banco banco);

	void remover(Banco banco);

	List<Banco> listarPorUsuario();

	public Banco buscar(Short id);
}
