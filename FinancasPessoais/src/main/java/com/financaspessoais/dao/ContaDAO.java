package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;

public class ContaDAO extends AbstractGenericDAO<Conta, Integer>{

	public ContaDAO() {
		super(Conta.class);
	}
	
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
}
