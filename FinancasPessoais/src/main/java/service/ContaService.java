package service;

import java.util.List;

import dominio.Conta;

public interface ContaService {
	Conta getContaPeloId(Long id);
	boolean inserirConta(Conta conta);
	public boolean deletarConta(Conta conta);
	public List<Conta> listarContas();
}
