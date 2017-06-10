package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.FaturaCartao;

public class FaturaCartaoDAO extends AbstractGenericDAO<FaturaCartao, Long>{

	public FaturaCartaoDAO() {
		super(FaturaCartao.class);
	}

	public List<FaturaCartao> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<FaturaCartao> listaFaturaCartao = (List<FaturaCartao>) entityManager
					.createQuery("SELECT f from FaturaCartao f " + " INNER JOIN f.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaFaturaCartao;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
