package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Transferencia;

public interface TransferenciaService {
	boolean criarOuAtualizar(Transferencia transferencia);

	void remover(Transferencia transferencia);

	List<Transferencia> listarPorUsuario();

	public Transferencia buscar(Long id);
}
