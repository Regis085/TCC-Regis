package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.model.Transferencia;
import com.financaspessoais.service.TransferenciaService;

public class TransferenciaServiceImpl implements TransferenciaService, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean criarOuAtualizar(Transferencia transferencia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remover(Transferencia transferencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Transferencia> listarPorUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transferencia buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
