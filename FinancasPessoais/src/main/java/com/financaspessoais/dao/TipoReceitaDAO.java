package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.TipoReceita;

@SuppressWarnings("unchecked")
public class TipoReceitaDAO extends AbstractGenericDAO<TipoReceita, Short> implements Serializable {
	private static final long serialVersionUID = 1L;

	public TipoReceitaDAO() {
		super(TipoReceita.class);
	}

	public List<TipoReceita> listarPorProprietario(Short idUsuario) {
//		try {
//			List<TipoReceita> listaTipoReceita = (List<TipoReceita>) entityManager
//					.createQuery("SELECT t from TipoReceita t " + " INNER JOIN t.proprietario u " + " WHERE u.id = :idUsuario")
//					.setParameter("idUsuario", idUsuario).getResultList();
//			return listaTipoReceita;
//		}
//		catch (NoResultException e) {
//			return null;
//		}
		return listarPorProprietarioENome(idUsuario, null);
	}
	
	public List<TipoReceita> listarPorProprietarioENome(Short idUsuario, String nome) {
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT t from TipoReceita t");
			consulta.append(" INNER JOIN t.proprietario u");
			consulta.append(" WHERE u.id = :idUsuario");
			
			if (nome != null) {
				consulta.append("   AND UPPER(t.nome) LIKE :nome");
			}
			
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			
			if (nome != null) {
				query.setParameter("nome", nome);
			}
			List<TipoReceita> listaTipoReceita = (List<TipoReceita>) query.getResultList();
			return listaTipoReceita;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public boolean validarDuplicidade(TipoReceita tipoReceita) throws Exception {

		Short id = tipoReceita.getId();
		String nome = tipoReceita.getNome();
		Short idUsuario = tipoReceita.getProprietario().getId();
		boolean isAtualizando = (id != null);

		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT t FROM TipoReceita t");
		consulta.append(" INNER JOIN t.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND t.nome = :nome");

		if (isAtualizando)
			consulta.append(" AND t.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome);

		if (isAtualizando)
			query.setParameter("id", id);

		List<TipoReceita> listaTipoReceita = (List<TipoReceita>) query.getResultList();
		
		Boolean isValido = listaTipoReceita.isEmpty();

		return isValido;
	}
}
