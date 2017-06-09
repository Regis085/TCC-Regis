package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.financaspessoais.model.Banco;
import com.financaspessoais.util.JpaUtil;

public class BancoDAO extends GenericDAO<Banco, Integer>{

	private EntityManager entityManager = JpaUtil.getEntityManager();
	
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

	public boolean excluir(Banco banco) {
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.remove(banco);
			transacao.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
