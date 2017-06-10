package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Despesa;

public class DespesaDAO extends AbstractGenericDAO<Despesa, Long>{

	public DespesaDAO() {
		super(Despesa.class);
	}

	public List<Despesa> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Despesa> listaDespesa = (List<Despesa>) entityManager
					.createQuery("SELECT d from Despesa d " + " INNER JOIN d.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaDespesa;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
