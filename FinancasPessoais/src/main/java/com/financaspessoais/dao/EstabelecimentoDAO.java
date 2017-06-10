package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Estabelecimento;

public class EstabelecimentoDAO extends AbstractGenericDAO<Estabelecimento, Long>{

	public EstabelecimentoDAO() {
		super(Estabelecimento.class);
	}

	public List<Estabelecimento> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Estabelecimento> listaEstabelecimento = (List<Estabelecimento>) entityManager
					.createQuery("SELECT e from Estabelecimento e " + " INNER JOIN e.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaEstabelecimento;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
