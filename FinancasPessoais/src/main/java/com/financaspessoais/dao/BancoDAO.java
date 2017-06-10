package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Banco;

public class BancoDAO extends AbstractGenericDAO<Banco, Integer>{

	public BancoDAO() {
		super(Banco.class);
	}
	
	public List<Banco> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Banco> listaBanco = (List<Banco>) entityManager
					.createQuery("SELECT b from Banco b " + " INNER JOIN b.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaBanco;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
