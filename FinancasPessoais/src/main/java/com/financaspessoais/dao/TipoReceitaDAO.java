package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.TipoReceita;

public class TipoReceitaDAO extends AbstractGenericDAO<TipoReceita, Short>{

	public TipoReceitaDAO() {
		super(TipoReceita.class);
	}

	public List<TipoReceita> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<TipoReceita> listaTipoReceita = (List<TipoReceita>) entityManager
					.createQuery("SELECT t from TipoReceita t " + " INNER JOIN t.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaTipoReceita;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
