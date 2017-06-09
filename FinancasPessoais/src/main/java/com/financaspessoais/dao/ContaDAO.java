package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.util.JpaUtil;

public class ContaDAO extends GenericDAO<Conta, Integer>{

	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public List<Conta> listarPorProprietario(Short idUsuario) {
		
		TipoConta tipoContaOutro = TipoConta.OUTRO;
		
		try {
			@SuppressWarnings("unchecked")
			List<Conta> listaConta = (List<Conta>) entityManager
					.createQuery("SELECT c from Conta c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario AND c.tipoConta = :tipoConta")
					.setParameter("idUsuario", idUsuario)
					.setParameter("tipoConta", tipoContaOutro).getResultList();
			return listaConta;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public boolean excluir(Conta conta) {
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.remove(conta);
			transacao.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
