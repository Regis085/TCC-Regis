package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Conta;

public interface ContaService {
	Conta getContaPeloId(Long id);
	boolean inserirConta(Conta conta);
	public boolean deletarConta(Conta conta);
	public List<Conta> listarContas();
	public List<Conta> listarContasPorUsuario();
}
