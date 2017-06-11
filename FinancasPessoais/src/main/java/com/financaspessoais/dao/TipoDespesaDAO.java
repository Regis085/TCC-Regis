package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.TipoDespesa;

@SuppressWarnings("unchecked")
public class TipoDespesaDAO extends AbstractGenericDAO<TipoDespesa, Short> {

	public TipoDespesaDAO() {
		super(TipoDespesa.class);
	}

	public List<TipoDespesa> listarPorProprietario(Short idUsuario) {
		try {
			List<TipoDespesa> listaTipoDespesa = (List<TipoDespesa>) entityManager
					.createQuery("SELECT t from TipoDespesa t " + " INNER JOIN t.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaTipoDespesa;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean validarDuplicidade(TipoDespesa tipoDespesa) throws Exception {
		
		Short id = tipoDespesa.getId();
		String nome = tipoDespesa.getNome();
		Short idUsuario = tipoDespesa.getProprietario().getId();
		boolean isAtualizando = (id != null);
		
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT t FROM TipoDespesa t");
		consulta.append(" INNER JOIN t.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND UPPER(t.nome) LIKE :nome");

		if (isAtualizando)
			consulta.append(" AND t.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome.toUpperCase());

		if (isAtualizando)
			query.setParameter("id", id);

		List<TipoDespesa> listaTipoDespesa = (List<TipoDespesa>) query.getResultList();
		
		Boolean isValido = listaTipoDespesa.isEmpty();

		return isValido;
	}
}
