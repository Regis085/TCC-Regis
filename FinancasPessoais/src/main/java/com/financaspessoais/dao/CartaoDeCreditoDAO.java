package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.CartaoDeCredito;

public class CartaoDeCreditoDAO extends AbstractGenericDAO<CartaoDeCredito, Short>{

	public CartaoDeCreditoDAO() {
		super(CartaoDeCredito.class);
	}

	public List<CartaoDeCredito> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<CartaoDeCredito> listaCartaoDeCredito = (List<CartaoDeCredito>) entityManager
					.createQuery("SELECT c from CartaoDeCredito c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaCartaoDeCredito;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
