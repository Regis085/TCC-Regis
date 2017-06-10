package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;

public class ContaBancariaDAO extends AbstractGenericDAO<ContaBancaria, Integer>{

	public ContaBancariaDAO() {
		super(ContaBancaria.class);
	}

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
}
