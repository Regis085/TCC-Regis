package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;

@SuppressWarnings("unchecked")
public class ContaBancariaDAO extends AbstractGenericDAO<ContaBancaria, Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	public ContaBancariaDAO() {
		super(ContaBancaria.class);
	}

	public List<ContaBancaria> listarPorProprietario(Short idUsuario) {
		
		TipoConta tipoContaBancaria = TipoConta.BANCARIA;
		
		try {
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

	public boolean validarDuplicidade(ContaBancaria contaBancaria) throws Exception {
		Integer id = contaBancaria.getId();
		String nome = contaBancaria.getNome();
		Short idUsuario = contaBancaria.getProprietario().getId();
		boolean isAtualizando = (id != null);

		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT c FROM ContaBancaria c");
		consulta.append(" INNER JOIN c.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND c.nome = :nome");

		if (isAtualizando)
			consulta.append(" AND c.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome);

		if (isAtualizando)
			query.setParameter("id", id);

		List<ContaBancaria> listaContaBancaria = (List<ContaBancaria>) query.getResultList();
		
		Boolean isValido = listaContaBancaria.isEmpty();

		return isValido;
	}
}
