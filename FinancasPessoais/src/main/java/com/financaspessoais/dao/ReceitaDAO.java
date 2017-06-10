package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Receita;

public class ReceitaDAO extends AbstractGenericDAO<Receita, Long>{

	public ReceitaDAO() {
		super(Receita.class);
	}

	public List<Receita> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Receita> listaReceita = (List<Receita>) entityManager
					.createQuery("SELECT r from Receita r " + " INNER JOIN r.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaReceita;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
