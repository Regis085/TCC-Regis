package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.CartaoDeCredito;

public interface CartaoDeCreditoService {
	boolean criarOuAtualizar(CartaoDeCredito cartaoDeCredito);

	void remover(CartaoDeCredito cartaoDeCredito);

	List<CartaoDeCredito> listarPorUsuario();

	public CartaoDeCredito buscar(Short id);
}
