package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.CartaoDeCredito;

@SuppressWarnings("unchecked")
public class CartaoDeCreditoDAO extends AbstractGenericDAO<CartaoDeCredito, Short> implements Serializable {
	private static final long serialVersionUID = 1L;

	public CartaoDeCreditoDAO() {
		super(CartaoDeCredito.class);
	}

	public List<CartaoDeCredito> listarPorProprietario(Short idUsuario) {
		try {
			List<CartaoDeCredito> listaCartaoDeCredito = (List<CartaoDeCredito>) entityManager
					.createQuery("SELECT c from CartaoDeCredito c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaCartaoDeCredito;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public boolean validarDuplicidade(CartaoDeCredito cartaoDeCredito) {
		Short id = cartaoDeCredito.getCodigoCartaoDeCredito();
		String nome = cartaoDeCredito.getNome();
		Short idUsuario = cartaoDeCredito.getProprietario().getId();
		boolean isAtualizando = (id != null);

		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT c FROM CartaoDeCredito c");
		consulta.append(" INNER JOIN c.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND c.nome = :nome");

		if (isAtualizando)
			consulta.append(" AND c.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome);

		if (isAtualizando)
			query.setParameter("id", id);

		List<CartaoDeCredito> listaCartaoDeCredito = (List<CartaoDeCredito>) query.getResultList();
		
		Boolean isValido = listaCartaoDeCredito.isEmpty();

		return isValido;
	}
}
