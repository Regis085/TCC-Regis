package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.TipoDespesa;

public class TipoDespesaDAO extends AbstractGenericDAO<TipoDespesa, Short>{

	public TipoDespesaDAO() {
		super(TipoDespesa.class);
	}

	public List<TipoDespesa> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<TipoDespesa> listaTipoDespesa = (List<TipoDespesa>) entityManager
					.createQuery("SELECT t from TipoDespesa t " + " INNER JOIN t.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaTipoDespesa;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
