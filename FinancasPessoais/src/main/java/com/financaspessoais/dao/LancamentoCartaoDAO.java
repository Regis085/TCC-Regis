package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.LancamentoCartao;

public class LancamentoCartaoDAO extends AbstractGenericDAO<LancamentoCartao, Long> {
	
	public LancamentoCartaoDAO() {
		super(LancamentoCartao.class);
	}

	public List<LancamentoCartao> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<LancamentoCartao> listaLancamentoCartao = (List<LancamentoCartao>) entityManager
					.createQuery("SELECT l from LancamentoCartao l " + " INNER JOIN l.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaLancamentoCartao;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
