package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Conta;

public interface ContaService {
	boolean inserirConta(Conta conta);
	public List<Conta> listarContas();
	public List<Conta> listarContasPorUsuario();
	public Conta buscarConta(Integer id);
	void excluirConta(Conta conta);
}
