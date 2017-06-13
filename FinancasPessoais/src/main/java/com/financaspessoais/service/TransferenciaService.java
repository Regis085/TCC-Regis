package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.Transferencia;

public interface TransferenciaService {
	boolean criarOuAtualizar(Transferencia transferencia);

	Transferencia buscar(Long id);

	List<Transferencia> listarPorUsuario();
	
	void remover(Transferencia transferencia);

}
