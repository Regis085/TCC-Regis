package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.util.JpaUtil;

public class ContaBancariaDAO extends GenericDAO<ContaBancaria, Integer>{

	private EntityManager entityManager = JpaUtil.getEntityManager();

	public List<ContaBancaria> listarPorProprietario(Short idUsuario) {
		
		TipoConta tipoContaBancaria = TipoConta.BANCARIA;
		
		try {
			@SuppressWarnings("unchecked")
			List<ContaBancaria> listaContaBancaria = (List<ContaBancaria>) entityManager
					.createQuery(
							"SELECT c from ContaBancaria c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario AND c.tipoConta = :tipoConta")
					.setParameter("idUsuario", idUsuario)
					.setParameter("tipoConta", tipoContaBancaria).getResultList();
			return listaContaBancaria;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean excluir(ContaBancaria contaBancaria) {
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.remove(contaBancaria);
			transacao.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
