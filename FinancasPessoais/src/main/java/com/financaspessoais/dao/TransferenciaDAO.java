package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Transferencia;

public class TransferenciaDAO extends AbstractGenericDAO<Transferencia, Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TransferenciaDAO() {
		super(Transferencia.class);
	}

	public List<Transferencia> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Transferencia> listaTransferencia = (List<Transferencia>) entityManager
					.createQuery("SELECT t from Transferencia t " + " INNER JOIN t.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaTransferencia;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
