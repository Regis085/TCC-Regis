package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Conta;

public interface ContaService {
	boolean criarOuAtualizar(Conta conta);

	public List<Conta> listarContas();

	public List<Conta> listarNaoBancariasPorUsuario();
	
	public List<Conta> listarPorUsuario();

	public Conta buscar(Integer id);

	void remover(Conta conta);
}
