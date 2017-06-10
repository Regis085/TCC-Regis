package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.CartaoDeCredito;

public class CartaoDeCreditoDAO extends AbstractGenericDAO<CartaoDeCredito, Short>{

	public CartaoDeCreditoDAO() {
		super(CartaoDeCredito.class);
	}

	public List<CartaoDeCredito> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
